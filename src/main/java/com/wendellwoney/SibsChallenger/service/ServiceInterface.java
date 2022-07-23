package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;

import java.util.List;

public interface ServiceInterface {
    ResponseListDto getAll();
    ResponseDto get(Long id);
    ResponseDto delete(Long id);
}
