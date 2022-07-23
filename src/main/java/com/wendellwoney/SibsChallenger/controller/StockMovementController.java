package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.service.StockMovementServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Stock Moviments")
@Tag(name = "Stock Moviments", description = "Handle With Stock Moviments")
@RestController
public class StockMovementController implements StockmovementControllerInterface {

    @Autowired
    private StockMovementServiceInterface stockMovimentService;

    @Override
    @ApiOperation(value = "This method return all stock moviments.")
    @GetMapping(value = "/stock-moviments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListDto> getAll() {
        ResponseListDto all = stockMovimentService.getAll();
        if (all == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(all);
    }

    @Override
    @ApiOperation(value = "This method return one stock moviment.")
    @GetMapping(value =  "/stock-moviment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> get(@PathVariable(value = "id") Long id) {
        ResponseDto item = stockMovimentService.get(id);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method create new stock moviments.")
    @PostMapping(value = "/stock-moviment",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody StockMovementPostDto stockMovementPostDto) {
        ResponseDto item = stockMovimentService.create(stockMovementPostDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method update stock moviment")
    @PatchMapping(value = "/stock-moviment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> update(@PathVariable(value = "id") Long id, @RequestBody StockMovementPostDto stockMovementPostDto) {
        StockMovementDto stockMovementDto = Mapper.config().map(stockMovementPostDto, StockMovementDto.class);
        stockMovementDto.setId(id);
        ResponseDto stock = stockMovimentService.update(stockMovementDto);
        if (stock.getHasError()) {
            return ResponseEntity.badRequest().body(stock);
        }

        return ResponseEntity.ok(stock);
    }

    @Override
    @ApiOperation(value = "This method remove stock moviment")
    @DeleteMapping(value = "/stock-moviment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> delete(@PathVariable(value = "id") Long id) {
        ResponseDto stock = stockMovimentService.delete(id);
        if (stock.getHasError()) {
            return ResponseEntity.badRequest().body(stock);
        }
        return ResponseEntity.ok(stock);
    }
}
