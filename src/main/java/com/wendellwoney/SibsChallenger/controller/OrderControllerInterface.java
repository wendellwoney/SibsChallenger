package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("${api.path}")
public interface OrderControllerInterface extends ControllerInterface {
    ResponseEntity<ResponseDto> create(OrderPostDto orderPostDto);
    ResponseEntity<ResponseDto> update(Long id, OrderUpdateDto orderPostDto);
    ResponseEntity<ResponseDto> process();
    ResponseEntity<ResponseDto> cancel(Long id, OrderCancelDto orderPostDto);
}
