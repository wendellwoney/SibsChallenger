package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import org.springframework.http.ResponseEntity;

public interface ControllerInterface {
    ResponseEntity<ResponseListDto> getAll();
    ResponseEntity<ResponseDto> get(Long id);
    ResponseEntity<ResponseDto> delete(Long id);
}
