package com.wendellwoney.SibsChallenger.dto.Request;

import java.io.Serializable;

public class StockMovementRequest implements Serializable {
    private Long id;
    private Long item;
    private Double quantity;
}
