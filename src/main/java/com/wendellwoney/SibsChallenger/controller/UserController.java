package com.wendellwoney.SibsChallenger.controller;

import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.service.UserServiceInterface;
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
public class UserController implements UserControllerInterface {

    @Autowired
    private UserServiceInterface userService;

    @Override
    @ApiOperation(value = "This method return all users.")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListDto> getAll() {
        ResponseListDto all = userService.getAll();
        if (all == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(all);
    }

    @Override
    @ApiOperation(value = "This method return one user.")
    @GetMapping(value =  "user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> get(@PathVariable(value = "id") Long id) {
        ResponseDto item = userService.get(id);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method create new user.")
    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody UserPostDto userPostDto) {
        ResponseDto item = userService.create(userPostDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method update user")
    @PatchMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> update(@PathVariable(value = "id") Long id, @RequestBody UserPostDto itemPostDto) {
        UserDto userDto = Mapper.config().map(itemPostDto, UserDto.class);
        userDto.setId(id);
        ResponseDto item = userService.update(userDto);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }

        return ResponseEntity.ok(item);
    }

    @Override
    @ApiOperation(value = "This method remove user")
    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> delete(@PathVariable(value = "id") Long id) {
        ResponseDto item = userService.delete(id);
        if (item.getHasError()) {
            return ResponseEntity.badRequest().body(item);
        }
        return ResponseEntity.ok(item);
    }
}
