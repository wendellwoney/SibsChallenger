package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.UserPostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${api.path}")
public interface UserControllerInterface extends ControllerInterface {
    ResponseEntity<ResponseDto> create(UserPostDto userPostDto);
    ResponseEntity<ResponseDto> update(Long id, UserPostDto userPostDto);
}
