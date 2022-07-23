package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.StockMovementPostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${api.path}")
public interface StockmovementControllerInterface extends ControllerInterface {
    ResponseEntity<ResponseDto> create(StockMovementPostDto item);
    ResponseEntity<ResponseDto> update(Long id, StockMovementPostDto item);
}
