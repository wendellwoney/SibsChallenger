package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import com.wendellwoney.SibsChallenger.dto.Stock;
import com.wendellwoney.SibsChallenger.mail.EmailComponent;
import com.wendellwoney.SibsChallenger.model.*;
import com.wendellwoney.SibsChallenger.repository.OrderRepository;
import com.wendellwoney.SibsChallenger.repository.StockMovementRepository;
import com.wendellwoney.SibsChallenger.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProcessOrderService implements ProcessOrderServiceInterface {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private OrderTracerService orderTracerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailComponent email;

    private static final Logger logger = LogManager.getLogger(ItemService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class, Exception.class, NullPointerException.class})
    public void process() throws Exception {
        try {
            List<Order> orders = orderRepository.ordersNotCompleted();
            List<Stock> filter = stockMovementRepository.getStockProduct().stream()
                    .map(o -> new Stock((Long) o[0], (Long) o[1], (Double) o[2])).collect(Collectors.toList());
            List<Stock> stockItens = filter.stream()
                    .filter(stock -> stock.getQuantity() > 0).collect(Collectors.toList());

            if (stockItens.size() == 0) {
                return;
            }

            for(Order order : orders) {

                int orderCompleted = 0;

                List<OrderTracer> ordersTracePersist = new ArrayList<>();
                for (OrderItem orderItem : order.getOrderItens()) {

                    Double quantityUsed = 0D;
                    if ( !Objects.isNull(order.getOrderTracers()) ) {
                        List<OrderTracer> orderTracerFilter = order.getOrderTracers().stream().filter(orderTracer ->
                                Objects.equals(orderTracer.getOrderItem().getId(), orderItem.getId())
                        ).collect(Collectors.toList());
                        quantityUsed = orderTracerFilter.stream().mapToDouble(OrderTracer::getQuantityUsed).sum();
                    }

                    Double quantityForComplete = orderItem.getQuantity() - quantityUsed;

                    if (quantityForComplete == 0) {
                        orderCompleted++;
                        continue;
                    }

                    List<Stock> stockFilter = stockItens.stream().filter(stock -> Objects.equals(stock.getItemID(),
                            orderItem.getItem().getId())).collect(Collectors.toList());

                    for (Stock stockFiltered : stockFilter) {
                        StockMovement stockMovement = stockMovementRepository.findById(stockFiltered.getStockMovementId())
                                .orElse(null);
                        if (stockFiltered.getQuantity() > 0 && stockFiltered.getQuantity() >= quantityForComplete) {
                            OrderTracer orderTracer = new OrderTracer(order, orderItem, stockMovement, quantityForComplete);
                            ordersTracePersist.add(orderTracer);
                            stockFiltered.setQuantity( (stockFiltered.getQuantity() - quantityForComplete) );
                            orderCompleted++;
                            break;
                        } else if (stockFiltered.getQuantity() > 0 && stockFiltered.getQuantity() < quantityForComplete) {
                            quantityForComplete = (quantityForComplete - stockFiltered.getQuantity());
                            OrderTracer orderTracer = new OrderTracer(order, orderItem, stockMovement, stockFiltered.getQuantity());
                            stockFiltered.setQuantity(0D);
                            ordersTracePersist.add(orderTracer);
                        }
                    }
                }

                orderTracerService.saveAll(ordersTracePersist);

                if (order.getOrderItens().size() == orderCompleted) {
                    orderRepository.setStatus(OrderStatusEnum.COMPLETED, order.getId());
                    logger.info("[ORDER PROCESS] ORDER NUMBER :" + order.getId() + " IN COMPLETED!");

                    if (Objects.isNull(order.getUser().getEmail())) {
                        User user = userRepository.findById(order.getUser().getId()).orElse(null);
                        order.setUser(user);
                    }

                    email.send(order.getUser().getEmail(), "Congratulations your order number "
                            + order.getId() + " has been completed", "Dear " + order.getUser().getName()
                            + " your order number " + order.getId() + " been completed");
                    logger.info("[EMAIL] EMAIL SENT TO :" + order.getUser().getName() +
                            " (" + order.getUser().getEmail() + ") ORDER " + order.getId() + " COMPLETED!");

                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("[ORDER PROCESS] Error to process order!");
        }
    }

}
