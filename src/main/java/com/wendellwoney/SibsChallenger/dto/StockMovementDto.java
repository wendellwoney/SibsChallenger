package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StockMovementDto implements Serializable {
    Long id;
    ItemDto item;
    Double quantity;
}
