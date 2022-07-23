package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.configuration.mapper.ItemMapper;
import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ItemPostDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import com.wendellwoney.SibsChallenger.service.ItemServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Item")
@Tag(name = "Item", description = "Handle With Itens")
@RestController
public class ItemController implements ItemControllerInterface {

    @Autowired
    private ItemServiceInterface itemService;

    @Override
    @ApiOperation(value = "This method return all itens.")
    @GetMapping(value = "/itens", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListDto> getAll() {
        ResponseListDto all = itemService.getAll();
        if (all == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(all);
    }

    @Override
    @ApiOperation(value = "This method return one item.")
    @GetMapping(value =  "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> get(@PathVariable(value = "id") Long id) {
        ResponseDto item = itemService.get(id);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method create new item.")
    @PostMapping(value = "item",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody ItemPostDto itemDto) {
        ResponseDto item = itemService.create(itemDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method update item")
    @PatchMapping(value = "item/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> update(@PathVariable(value = "id") Long id, @RequestBody ItemPostDto itemPostDto) {
        ItemDto itemDto = ItemMapper.Mapper().map(itemPostDto, ItemDto.class);
        itemDto.setId(id);
        ResponseDto item = itemService.update(itemDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method remove item")
    @DeleteMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> delete(@PathVariable(value = "id") Long id) {
        ResponseDto item = itemService.delete(id);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }
        return ResponseEntity.ok(item);
    }
}
