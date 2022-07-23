package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ItemPostDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerInterface {
    ResponseEntity<ResponseListDto> getAll();
    ResponseEntity<ResponseDto> get(Long id);
    ResponseEntity<ResponseDto> delete(Long id);
}
