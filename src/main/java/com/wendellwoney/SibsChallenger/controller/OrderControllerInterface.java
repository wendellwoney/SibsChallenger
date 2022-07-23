package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.OrderPostDto;
import com.wendellwoney.SibsChallenger.dto.OrderUpdateDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${api.path}")
public interface OrderControllerInterface extends ControllerInterface {
    ResponseEntity<ResponseDto> create(OrderPostDto orderPostDto);
    ResponseEntity<ResponseDto> update(Long id, OrderUpdateDto orderPostDto);
}
