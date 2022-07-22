package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class StockMovementDto implements Serializable {
    Long id;
    Long itemID;
    Double quantity;
}
