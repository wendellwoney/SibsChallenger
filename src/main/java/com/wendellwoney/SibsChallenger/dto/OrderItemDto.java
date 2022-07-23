package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    Long id;
    ItemDto item;
    Double quantity;
}