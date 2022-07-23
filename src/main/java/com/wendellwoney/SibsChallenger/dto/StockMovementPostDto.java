package com.wendellwoney.SibsChallenger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockMovementPostDto implements Serializable {
    Long itemID;
    Double quantity;
}
