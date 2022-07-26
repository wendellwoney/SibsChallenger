package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.model.OrderTracer;
import com.wendellwoney.SibsChallenger.repository.OrderTracerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderTracerService implements OrderTracerServiceInterface{
    @Autowired
    private OrderTracerRepository repository;

    @Transactional
    public List<OrderTracer> saveAll(List<OrderTracer> orderTracers) {
        return repository.saveAll(orderTracers);
    }
}
