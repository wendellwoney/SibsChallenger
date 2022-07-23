package com.wendellwoney.SibsChallenger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseListDto {
    private Boolean hasError;
    private List<?> response;

    public ResponseListDto(Boolean hasError, List<?> response) {
        this.hasError = hasError;
        this.response = response;
    }
}
