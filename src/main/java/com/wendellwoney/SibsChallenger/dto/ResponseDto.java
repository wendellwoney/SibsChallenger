package com.wendellwoney.SibsChallenger.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ResponseDto {
    private Boolean hasError;
    private Object response;

    public ResponseDto(Boolean hasError, Object response) {
        this.hasError = hasError;
        this.response = response;
    }
}
