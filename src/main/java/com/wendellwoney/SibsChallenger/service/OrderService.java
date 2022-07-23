package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.Utils;
import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.model.Order;
import com.wendellwoney.SibsChallenger.model.OrderItem;
import com.wendellwoney.SibsChallenger.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private OrderRepository repository;

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
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to get order!");
        }
    }

    @Override
    public ResponseDto create(OrderPostDto orderPostDto) {
        try {
            Order order = Mapper.config().map(orderPostDto, Order.class);
            if(order == null) {
                logger.error("Error to create new order [map return null]");
                return new ResponseDto(true, "Error to create new order!");
            }
            Order persist = this.createOrder(order);
            return new ResponseDto(false, Mapper.config().map(persist, OrderDto.class) );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to create order!");
        }
    }

    @Transactional
    public Order createOrder(Order order) {
        Order clone = order.clone();
        clone.setOrderItens(null);
        Order persist = repository.save(clone);

        order.getOrderItens().forEach(orderItem -> {
            orderItem.setOrder(persist);
            orderItem.setId(null);
        });
        List<OrderItem> listPersist = orderItemService.createByList(order.getOrderItens());
        persist.setOrderItens(listPersist);

        return persist;
    }

    @Override
    public ResponseDto update(OrderDto orderDto) {
        try {
            Order check = repository.findById(orderDto.getId()).orElse(null);

            if (check == null) {
                throw new Exception("Order id not found for update item");
            }

            Order order = Mapper.config().map(orderDto, Order.class);

            if(order == null) {
                logger.error("Error to update order [map return null]");
                return new ResponseDto(true, "Error to update order!");
            }

            Utils.comparAndIgnoreNull(order, check);

            Order persist = repository.save(check);
            return new ResponseDto(false, Mapper.config().map(persist, OrderDto.class) );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to update order!");
        }
    }

    @Override
    public ResponseDto delete(Long id) {
        try {
            repository.deleteById(id);
            return new ResponseDto(false, "Order removed!");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to delete order!");
        }
    }
}
