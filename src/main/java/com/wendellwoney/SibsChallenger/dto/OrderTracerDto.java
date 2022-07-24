package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTracerDto {
    Long id;
    Long stockMovementID;
    Double quantityUsed;
}
