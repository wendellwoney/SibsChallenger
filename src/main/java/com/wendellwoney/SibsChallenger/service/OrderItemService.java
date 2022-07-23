package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.model.OrderItem;
import com.wendellwoney.SibsChallenger.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemService implements OrderItemServiceInterface {

    @Autowired
    private OrderItemRepository repository;

    @Transactional
    public List<OrderItem> createUpdateByList(List<OrderItem> orderItens) {
        List<OrderItem> saves = repository.saveAll(orderItens);
        return saves;
    }

}
