package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.Utils;
import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.model.StockMovement;
import com.wendellwoney.SibsChallenger.repository.StockMovementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockMovementService implements StockMovementServiceInterface {

    @Autowired
    private StockMovementRepository repository;

    @Autowired
    private ProcessOrderServiceInterface processOrderService;

    private static final Logger logger = LogManager.getLogger(StockMovementService.class);

    @Override
    public ResponseListDto getAll() {
        try {
            List<StockMovement> stockMovements = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            List<StockMovementDto> stockMovementsDto = new ArrayList<>();

            if (stockMovements.size() > 0) {
                stockMovementsDto = stockMovements.stream().map(entity -> Mapper.config().map(entity, StockMovementDto.class)).collect(Collectors.toList());
            }

            return  new ResponseListDto(false, stockMovementsDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseDto get(Long id) {
        try {
            StockMovement stockMovement = repository.findById(id).orElse(null);
            if (stockMovement == null) {
                return new ResponseDto(true, "Stock moviment not found!");
            }
            return new ResponseDto(false, Mapper.config().map(stockMovement, StockMovementDto.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to get Stock moviment!");
        }
    }

    public StockMovement getId (Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ResponseDto create(StockMovementPostDto stockMovementPostDto) {
        try {
            StockMovement stockMovement = Mapper.config().map(stockMovementPostDto, StockMovement.class);
            if(stockMovement == null) {
                logger.error("Error to create new stock moviment [map return null]");
                return new ResponseDto(true, "Error to create new stock moviment!");
            }

            stockMovement.setId(null);
            StockMovement persist = repository.save(stockMovement);
            processOrderService.process();
            return this.get(persist.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to create stock moviment!");
        }
    }

    @Override
    public ResponseDto update(StockMovementDto stockMovementDto) {
        try {
            StockMovement check = repository.findById(stockMovementDto.getId()).orElse(null);

            if (check == null) {
                throw new Exception("Item id not found for update item");
            }

            StockMovement stockMovement = Mapper.config().map(stockMovementDto, StockMovement.class);

            if(stockMovement == null) {
                logger.error("Error to update stock Moviment [map return null]");
                return new ResponseDto(true, "Error update stock moviment!");
            }

            Utils.comparAndIgnoreNull(stockMovement, check);

            StockMovement persist = repository.save(check);
            processOrderService.process();
            return new ResponseDto(false, Mapper.config().map(persist, StockMovementDto.class) );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error update stock moviment!");
        }
    }

    @Override
    public ResponseDto delete(Long id) {
        try {
            repository.deleteById(id);
            return new ResponseDto(false, "Stock moviment removed!" );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error remove Stock moviment!");
        }
    }
}
