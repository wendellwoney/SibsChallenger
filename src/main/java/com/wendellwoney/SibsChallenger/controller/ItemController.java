package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import com.wendellwoney.SibsChallenger.service.ItemServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Item", position = 1)
@Tag(name = "Item", description = "Handle With Itens")
@RestController
public class ItemController implements ItemControllerInterface {

    @Autowired
    private ItemServiceInterface itemService;

    @Override
    @GetMapping(value = "/itens", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListDto> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @Override
    @GetMapping(value =  "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> get() {
        return null;
    }

    @Override
    @PostMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(ItemDto item) {
        return null;
    }

    @Override
    @PutMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> update(ItemDto item) {
        return null;
    }

    @Override
    @DeleteMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> delete(Long item) {
        return null;
    }
}
