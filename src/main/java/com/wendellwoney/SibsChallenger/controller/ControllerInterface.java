package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerInterface {
    ResponseEntity<ResponseListDto> getAll();
    ResponseEntity<ResponseDto> get();
    ResponseEntity<ResponseDto> create(ItemDto item);
    ResponseEntity<ResponseDto> update(ItemDto item);
    ResponseEntity<Boolean> delete(Long item);
}
