package com.wendellwoney.SibsChallenger.configuration.mapper;

import org.modelmapper.ModelMapper;

public abstract class MapperConfig {
    public static ModelMapper config() {
        return new ModelMapper();
    }
}
