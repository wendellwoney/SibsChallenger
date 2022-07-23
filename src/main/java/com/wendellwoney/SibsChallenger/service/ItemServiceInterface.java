package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ItemPostDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;

public interface ItemServiceInterface extends ServiceInterface{
    ResponseDto create(ItemPostDto item);
    ResponseDto update(ItemDto item);
}
