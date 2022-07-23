package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderPostDto {
    Long userID;
    List<OrderItemPostDto> itens;
}
