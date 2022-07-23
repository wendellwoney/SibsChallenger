package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.dto.*;

public interface UserServiceInterface extends ServiceInterface{
    ResponseDto create(UserPostDto userPostDto);
    ResponseDto update(UserDto userDto);
}
