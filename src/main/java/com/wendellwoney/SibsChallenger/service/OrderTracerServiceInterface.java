package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.model.OrderTracer;

import java.util.List;

public interface OrderTracerServiceInterface {
    List<OrderTracer> saveAll(List<OrderTracer> orderTracers);
}
