package com.wendellwoney.SibsChallenger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto {
    Long id;
    String name;
    String description;
}
