package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Item", position = 1)
@Tag(name = "Item", description = "Handle With Itens")
@RestController
public class ItemController implements ItemControllerInterface {

    @Override
    @GetMapping("/itens")
    public ResponseEntity<ResponseListDto> getAll() {
        return null;
    }

    @Override
    @GetMapping("item/{id}")
    public ResponseEntity<ResponseDto> get() {
        return null;
    }

    @Override
    @PostMapping("item")
    public ResponseEntity<ResponseDto> create(ItemDto item) {
        return null;
    }

    @Override
    @PutMapping("item")
    public ResponseEntity<ResponseDto> update(ItemDto item) {
        return null;
    }

    @Override
    @DeleteMapping("item/{id}")
    public ResponseEntity<Boolean> delete(Long item) {
        return null;
    }
}
