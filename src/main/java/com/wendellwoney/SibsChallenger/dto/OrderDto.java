package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    Long id;
    UserDto user;
    List<OrderItemDto> orderItens;
}
