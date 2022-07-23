package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.model.OrderItem;

import java.util.List;

public interface OrderItemServiceInterface {
    List<OrderItem> createUpdateByList(List<OrderItem> orderItens);

}
