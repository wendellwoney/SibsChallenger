package com.wendellwoney.SibsChallenger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private Boolean hasError;
    private Object response;

    public ResponseDto(Boolean hasError, Object response) {
        this.hasError = hasError;
        this.response = response;
    }
}
