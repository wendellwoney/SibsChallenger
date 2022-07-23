package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class OrderUpdateDto {
    Long userID;
    List<OrderItemUpdateDto> itens;
}
