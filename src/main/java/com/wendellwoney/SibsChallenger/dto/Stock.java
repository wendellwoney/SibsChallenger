package com.wendellwoney.SibsChallenger.dto;

import com.wendellwoney.SibsChallenger.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    Long stockMovementId;
    Long itemID;
    Double quantity;
}
