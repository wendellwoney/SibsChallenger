package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.service.OrderService;
import com.wendellwoney.SibsChallenger.service.OrderServiceInterface;
import com.wendellwoney.SibsChallenger.service.ProcessOrderServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Orders")
@Tag(name = "Orders", description = "Handle With Orders")
@RestController
public class OrderController implements OrderControllerInterface {

    @Autowired
    private OrderServiceInterface orderService;

    @Autowired
    private ProcessOrderServiceInterface orderProcessService;

    @Autowired
    private ProcessOrderServiceInterface processOrderService;

    private static final Logger logger = LogManager.getLogger(OrderController.class);

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

        try {
            processOrderService.process();
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method cancel any order.")
    @PostMapping(value = "/order/{id}/cancel",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> cancel(@PathVariable(value = "id") Long id, @Valid @RequestBody OrderCancelDto orderCancelDto) {
        orderCancelDto.setOrderID(id);
        ResponseDto item = orderService.cancel(orderCancelDto);
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

        try {
            processOrderService.process();
        } catch (Exception e) {
            logger.error("[ORDER SERVICE] " + e.getMessage());
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

    @Override
    @ApiOperation(value = "This method process all orders.")
    @GetMapping(value = "/orders/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> process() {
        try {
            orderProcessService.process();
            return ResponseEntity.ok(new ResponseDto(false, "Process order completed"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDto(true, "Error to Process orders"));
        }
    }
}
