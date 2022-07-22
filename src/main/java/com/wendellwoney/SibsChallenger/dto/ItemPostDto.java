package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemPostDto {

    @NotBlank(message = "[name] cannot be blank")
    String name;
    @NotBlank(message = "[description] cannot be blank")
    String description;
}
