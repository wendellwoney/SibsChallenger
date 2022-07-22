package com.wendellwoney.SibsChallenger.configuration.mapper;

import com.wendellwoney.SibsChallenger.dto.StockMovementDto;
import com.wendellwoney.SibsChallenger.model.Item;
import com.wendellwoney.SibsChallenger.model.StockMovement;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class StockMovementMapper extends MapperConfig {

    public static ModelMapper Mapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Long, Item> idToItem = ctx -> new Item(ctx.getSource());
        TypeMap<StockMovementDto, StockMovement> propertyMapper =
                modelMapper.createTypeMap(StockMovementDto.class, StockMovement.class);

        propertyMapper.addMappings(mapper -> {
            mapper.using(idToItem).map(StockMovementDto::getItemID, StockMovement::setItem);
        });

        return modelMapper;
    }

}
