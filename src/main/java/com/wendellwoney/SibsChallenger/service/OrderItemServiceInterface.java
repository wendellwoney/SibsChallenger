package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import com.wendellwoney.SibsChallenger.model.OrderItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemServiceInterface {
    List<OrderItem> createUpdateByList(List<OrderItem> orderItens);

}
