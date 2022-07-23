package com.wendellwoney.SibsChallenger.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderItemUpdateDto {
    @NotNull
    @NotEmpty
    Long id;

    Long itemID;

    Double quantity;
}
