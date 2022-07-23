package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    Long id;
    ItemDto item;
    Double quantity;
}