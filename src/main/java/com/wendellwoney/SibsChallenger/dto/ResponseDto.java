package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ResponseDto {
    private Boolean hasError;
    private Object response;
}
