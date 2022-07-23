package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.dto.*;

public interface StockMovementServiceInterface extends ServiceInterface{
    ResponseDto create(StockMovementPostDto stockMovementPostDto);
    ResponseDto update(StockMovementDto stockMovementDto);
}
