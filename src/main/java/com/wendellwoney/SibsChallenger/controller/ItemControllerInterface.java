package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ItemPostDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("${api.path}")
public interface ItemControllerInterface extends ControllerInterface {
    ResponseEntity<ResponseDto> create(ItemPostDto item);
    ResponseEntity<ResponseDto> update(Long id, ItemPostDto item);
}
