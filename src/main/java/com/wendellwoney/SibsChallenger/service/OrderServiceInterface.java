package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.dto.*;

public interface OrderServiceInterface extends ServiceInterface{
    ResponseDto create(OrderPostDto orderPostDto);
    ResponseDto update(OrderDto orderDto);
}
