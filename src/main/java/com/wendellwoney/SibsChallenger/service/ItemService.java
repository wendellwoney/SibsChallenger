package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.configuration.mapper.ItemMapper;
import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import com.wendellwoney.SibsChallenger.model.Item;
import com.wendellwoney.SibsChallenger.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService implements ItemServiceInterface{

    @Autowired
    private ItemRepository repository;

    private static final Logger logger = LogManager.getLogger(ItemService.class);

    @Override
    public ResponseListDto getAll() {
        try {
            List<Item> itens = repository.findAll();
            List<ItemDto> itensDto = new ArrayList<>();

            if (itens.size() > 0) {
               itensDto = itens.stream().map(entity -> ItemMapper.Mapper().map(entity, ItemDto.class)).collect(Collectors.toList());
            }

            return  new ResponseListDto(false, itensDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseDto get() {
        return null;
    }

    @Override
    public ResponseDto create(ItemDto item) {
        return null;
    }

    @Override
    public ResponseDto update(ItemDto item) {
        return null;
    }

    @Override
    public Boolean delete(Long item) {
        return null;
    }
}
