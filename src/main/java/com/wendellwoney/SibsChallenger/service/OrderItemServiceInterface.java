package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.model.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderItemServiceInterface {
    List<OrderItem> createUpdateByList(List<OrderItem> orderItens);

}
