package com.wendellwoney.SibsChallenger.dto;

import com.wendellwoney.SibsChallenger.configuration.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    Long id;
    OrderStatusEnum status;
    String note;
    UserDto user;
    List<OrderItemDto> orderItens;
    List<OrderTracerDto> orderTracers;
}
