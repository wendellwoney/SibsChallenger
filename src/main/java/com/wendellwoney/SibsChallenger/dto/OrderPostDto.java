package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderPostDto {
    Long userID;
    List<OrderItemPostDto> itens;
}
