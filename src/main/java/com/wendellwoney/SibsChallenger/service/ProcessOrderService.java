package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.dto.Stock;
import com.wendellwoney.SibsChallenger.mail.EmailComponent;
import com.wendellwoney.SibsChallenger.model.Order;
import com.wendellwoney.SibsChallenger.model.OrderItem;
import com.wendellwoney.SibsChallenger.model.OrderTracer;
import com.wendellwoney.SibsChallenger.model.StockMovement;
import com.wendellwoney.SibsChallenger.repository.OrderRepository;
import com.wendellwoney.SibsChallenger.repository.StockMovementRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private static final Logger logger = LogManager.getLogger(ItemService.class);

    @Transactional
    public void process() {
        try {
            List<Order> orders = orderRepository.ordersNotCompleted();
            List<Stock> filter = stockMovementRepository.getStockProduct().stream().map(o -> {
                Stock st = new Stock((Long) o[0], (Long) o[1], (Double) o[2]);
                return st;
            }).collect(Collectors.toList());
            List<Stock> stockItens = filter.stream()
                    .filter(stock -> stock.getQuantity() > 0).collect(Collectors.toList());

            for(Order order : orders) {

                int orderCompleted = 0;

                List<OrderTracer> ordersTracePersist = new ArrayList<>();
                for (OrderItem orderItem : order.getOrderItens()) {

                    List<OrderTracer> orderTracerFilter = order.getOrderTracers().stream().filter(orderTracer ->
                            Objects.equals(orderTracer.getOrderItem().getId(), orderItem.getId())
                    ).collect(Collectors.toList());

                    Double quantityUsed = orderTracerFilter.stream().mapToDouble(OrderTracer::getQuantityUsed).sum();
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
                            quantityForComplete = quantityForComplete - stockFiltered.getQuantity();
                            stockFiltered.setQuantity(0D);
                            OrderTracer orderTracer = new OrderTracer(order, orderItem, stockMovement, stockFiltered.getQuantity());
                            ordersTracePersist.add(orderTracer);
                        }
                    }

                    orderTracerService.saveAll(ordersTracePersist);

                    if (order.getOrderItens().size() == orderCompleted) {
                        Order orderUpdate = orderRepository.setCompleted(order.getId());
                        if (!orderUpdate.getCompleted()) {
                            throw new Exception("Error to complete Order");
                        }

                        EmailComponent email = new EmailComponent();
                        email.sendSimpleMessage(order.getUser().getEmail(), "Congratulations your order number"
                                + order.getId() + "has been completed", "Dear " + order.getUser().getName()
                                + " your order number " + order.getId() + " been completed");

                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
