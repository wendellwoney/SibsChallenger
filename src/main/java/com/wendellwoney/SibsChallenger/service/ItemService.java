package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.Utils;
import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.ItemDto;
import com.wendellwoney.SibsChallenger.dto.ItemPostDto;
import com.wendellwoney.SibsChallenger.dto.ResponseDto;
import com.wendellwoney.SibsChallenger.dto.ResponseListDto;
import com.wendellwoney.SibsChallenger.model.Item;
import com.wendellwoney.SibsChallenger.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
            List<Item> itens = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            List<ItemDto> itensDto = new ArrayList<>();

            if (itens.size() > 0) {
               itensDto = itens.stream().map(entity -> Mapper.config().map(entity, ItemDto.class)).collect(Collectors.toList());
            }

            return  new ResponseListDto(false, itensDto);
        } catch (Exception e) {
            logger.error("[ITEM SERVICE] " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseDto get(Long id) {
        try {
            Item item = repository.findById(id).orElse(null);
            if (item == null) {
                return new ResponseDto(true, "Item not found!");
            }
            return new ResponseDto(false, Mapper.config().map(item, ItemDto.class));
        } catch (Exception e) {
            logger.error("[ITEM SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to get item!");
        }
    }

    @Override
    public ResponseDto create(ItemPostDto itemDto) {
        try {
            Item item = Mapper.config().map(itemDto, Item.class);
            if(item == null) {
                logger.error("[ITEM SERVICE] Error to create new item [map return null]");
                return new ResponseDto(true, "Error to create new item!");
            }
            Item persist = repository.save(item);
            return new ResponseDto(false, Mapper.config().map(persist, ItemDto.class) );
        } catch (Exception e) {
            logger.error("[ITEM SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to create item!");
        }
    }

    @Override
    public ResponseDto update(ItemDto itemDto) {
        try {
            Item check = repository.findById(itemDto.getId()).orElse(null);

            if (check == null) {
                throw new Exception("Item id not found for update item");
            }

            Item item = Mapper.config().map(itemDto, Item.class);

            if(item == null) {
                logger.error("[ITEM SERVICE] Error to update item [map return null]");
                return new ResponseDto(true, "Error to update item!");
            }

            Utils.comparAndIgnoreNull(item, check);

            Item persist = repository.save(check);
            return new ResponseDto(false, Mapper.config().map(persist, ItemDto.class) );
        } catch (Exception e) {
            logger.error("[ITEM SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to update item!");
        }
    }

    @Override
    public ResponseDto delete(Long item) {
        try {
            repository.deleteById(item);
            return new ResponseDto(false, "Item removed!" );
        } catch (Exception e) {
            logger.error("[ITEM SERVICE] " + e.getMessage());
            return new ResponseDto(true, "Error to delete item!");
        }
    }
}
