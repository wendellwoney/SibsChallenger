package com.wendellwoney.SibsChallenger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockMovementPostDto implements Serializable {
    Long itemID;
    Double quantity;
}
