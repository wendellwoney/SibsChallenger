package com.wendellwoney.SibsChallenger.dto;

import com.wendellwoney.SibsChallenger.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListDto {
    private Boolean hasError;
    private List<?> response;

    public ResponseListDto(Boolean hasError, List<?> response) {
        this.hasError = hasError;
        this.response = response;
    }
}
