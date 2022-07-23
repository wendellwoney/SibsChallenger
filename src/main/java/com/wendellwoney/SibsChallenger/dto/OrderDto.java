package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    Long id;
    UserDto user;
    Boolean completed;
    List<OrderItemDto> orderItens;
}