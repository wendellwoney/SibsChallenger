package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.Utils;
import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.model.Order;
import com.wendellwoney.SibsChallenger.model.OrderItem;
import com.wendellwoney.SibsChallenger.repository.OrderRepository;
import com.wendellwoney.SibsChallenger.repository.OrderTracerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderTracerRepository orderTracerRepository;

    @Autowired
    private OrderItemServiceInterface orderItemService;

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Override
    public ResponseListDto getAll() {
        try {
            List<Order> orders = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            List<OrderDto> orderDtos = new ArrayList<>();

            if (orders.size() > 0) {
                orderDtos = orders.stream().map(entity -> Mapper.config().map(entity, OrderDto.class)).collect(Collectors.toList());
            }

            return  new ResponseListDto(false, orderDtos);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseDto get(Long id) {
        try {
            Order order = repository.findById(id).orElse(null);
            if (order == null) {
                return new ResponseDto(true, "Order not found!");
            }
            return new ResponseDto(false, Mapper.config().map(order, OrderDto.class));
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to get order!");
        }
    }

    @Override
    public ResponseDto create(OrderPostDto orderPostDto) {
        try {
            Order order = Mapper.config().map(orderPostDto, Order.class);
            if(order == null) {
                logger.error("[ORDER SERVICE] Error to create new order [map return null]");
                return new ResponseDto(true, "Error to create new order!");
            }
            Order persist = this.createOrder(order);
            return new ResponseDto(false, Mapper.config().map(persist, OrderDto.class) );
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to create order!");
        }
    }

    @Transactional
    public Order createOrder(Order order) throws CloneNotSupportedException {
        Order clone = order.clone();
        clone.setId(null);
        clone.setStatus(OrderStatusEnum.INPROGESS);
        clone.setOrderItens(null);
        Order persist = repository.save(clone);

        order.getOrderItens().forEach(orderItem -> {
            orderItem.setOrder(persist);
            orderItem.setId(null);
        });
        List<OrderItem> listPersist = orderItemService.createUpdateByList(order.getOrderItens());
        persist.setOrderItens(listPersist);

        return persist;
    }

    @Override
    public ResponseDto update(OrderDto orderDto) {
        try {
            if (orderDto.getOrderItens() != null && orderDto.getOrderItens().size() > 0) {
                orderDto.getOrderItens().forEach(orderItemDto -> {
                    if (orderItemDto.getId() == null) {
                        logger.error("[ORDER SERVICE] Order Item id can't null");
                        try {
                            throw new Exception("Order Item id can't null");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            Order check = repository.findById(orderDto.getId()).orElse(null);

            if (check.getStatus() == OrderStatusEnum.COMPLETED) {
                logger.error("[ORDER SERVICE] Order id " + check.getId() + "is completed");
                throw new Exception("Order id " + check.getId() + "is completed");
            }

            if (check == null) {
                logger.error("[ORDER SERVICE] Order id not found for update item");
                throw new Exception("Order id not found for update item");
            }

            Order order = Mapper.config().map(orderDto, Order.class);
            if(order == null) {
                logger.error("[ORDER SERVICE] Error to update order [map return null]");
                return new ResponseDto(true, "Error to update order!");
            }

            Utils.comparAndIgnoreNull(order, check);

            Order persist = this.updateOrder(check);
            return new ResponseDto(false, Mapper.config().map(persist, OrderDto.class) );
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to update order!");
        }
    }

    @Transactional
    public Order updateOrder(Order order) {
        List<OrderItem> orderList = new ArrayList<>();
        if ( order.getOrderItens() != null) {
            orderList = order.getOrderItens();
        }
        order.setOrderItens(null);
        Order persist = repository.save(order);

        if (orderList.size() > 0) {
            orderList.forEach(orderItem -> orderItem.setOrder(persist));

            List<OrderItem> listPersist = orderItemService.createUpdateByList(orderList);
            persist.setOrderItens(listPersist);
        }

        return persist;
    }

    @Override
    public ResponseDto delete(Long id) {
        try {
            repository.setStatus(OrderStatusEnum.REMOVED, id);
            orderTracerRepository.cancelOrder(id);
            repository.deleteById(id);
            return new ResponseDto(false, "Order removed!");
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to delete order!");
        }
    }

    @Transactional
    public ResponseDto cancel(OrderCancelDto orderCancel) {
        try {
            Order order = repository.findById(orderCancel.getOrderID()).orElse(null);

            if (order == null) {
                return new ResponseDto(true, "Order " + orderCancel.getOrderID() + " not found!");
            }

            repository.cancel(OrderStatusEnum.CANCELED, orderCancel.getMotivation(), orderCancel.getOrderID());
            orderTracerRepository.cancelOrder(orderCancel.getOrderID());
            return new ResponseDto(false, "Order canceled!");
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to cancel order!");
        }
    }
}
