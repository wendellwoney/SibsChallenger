package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.service.OrderServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Users")
@Tag(name = "Users", description = "Handle With Itens")
@RestController
public class OrderController implements OrderControllerInterface {

    @Autowired
    private OrderServiceInterface orderService;

    @Override
    @ApiOperation(value = "This method return all orders.")
    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListDto> getAll() {
        ResponseListDto all = orderService.getAll();
        if (all == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(all);
    }

    @Override
    @ApiOperation(value = "This method return one order.")
    @GetMapping(value =  "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> get(@PathVariable(value = "id") Long id) {
        ResponseDto item = orderService.get(id);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method create new order.")
    @PostMapping(value = "/order",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody OrderPostDto orderPostDto) {
        ResponseDto item = orderService.create(orderPostDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method update order")
    @PatchMapping(value = "/order/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody OrderUpdateDto orderUpdateDto) {
        OrderDto orderDto = Mapper.config().map(orderUpdateDto, OrderDto.class);
        orderDto.setId(id);
        ResponseDto item = orderService.update(orderDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method remove order")
    @DeleteMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> delete(@PathVariable(value = "id") Long id) {
        ResponseDto order = orderService.delete(id);
        if (order.getHasError()) {
            return ResponseEntity.badRequest().body(order);
        }
        return ResponseEntity.ok(order);
    }
}
