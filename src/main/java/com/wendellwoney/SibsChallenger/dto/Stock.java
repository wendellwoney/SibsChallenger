package com.wendellwoney.SibsChallenger.dto;

import com.wendellwoney.SibsChallenger.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    Long stockMovementId;
    Item item;
    Double quantity;
}
