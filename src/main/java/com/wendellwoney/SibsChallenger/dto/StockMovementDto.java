package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockMovementDto implements Serializable {
    Long id;
    ItemDto item;
    Double quantity;
}
