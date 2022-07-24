package com.wendellwoney.SibsChallenger.service;

import com.wendellwoney.SibsChallenger.Utils;
import com.wendellwoney.SibsChallenger.configuration.mapper.Mapper;
import com.wendellwoney.SibsChallenger.dto.*;
import com.wendellwoney.SibsChallenger.model.StockMovement;
import com.wendellwoney.SibsChallenger.model.User;
import com.wendellwoney.SibsChallenger.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository repository;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Override
    public ResponseListDto getAll() {
        try {
            List<User> users = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            List<UserDto> usersDto = new ArrayList<>();

            if (users.size() > 0) {
                usersDto = users.stream().map(entity -> Mapper.config().map(entity, UserDto.class)).collect(Collectors.toList());
            }

            return  new ResponseListDto(false, usersDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseDto get(Long id) {
        try {
            User user = repository.findById(id).orElse(null);
            if (user == null) {
                return new ResponseDto(true, "User not found!");
            }
            return new ResponseDto(false, Mapper.config().map(user, UserDto.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to get user!");
        }
    }

    @Override
    public ResponseDto create(UserPostDto userPostDto) {
        try {
            User user = Mapper.config().map(userPostDto, User.class);
            if(user == null) {
                logger.error("Error to create new user [map return null]");
                return new ResponseDto(true, "Error to create new user!");
            }
            User persist = repository.save(user);
            return new ResponseDto(false, Mapper.config().map(persist, UserDto.class) );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to create item!");
        }
    }

    @Override
    public ResponseDto update(UserDto userDto) {
        try {
            User check = repository.findById(userDto.getId()).orElse(null);

            if (check == null) {
                throw new Exception("User id not found for update item");
            }

            User user = Mapper.config().map(userDto, User.class);

            if(user == null) {
                logger.error("Error to update user [map return null]");
                return new ResponseDto(true, "Error to update user!");
            }

            Utils.comparAndIgnoreNull(user, check);

            User persist = repository.save(check);
            return new ResponseDto(false, Mapper.config().map(persist, UserDto.class) );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to update user!");
        }
    }

    @Override
    public ResponseDto delete(Long id) {
        try {
            repository.deleteById(id);
            return new ResponseDto(false, "User removed!" );
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseDto(true, "Error to delete user!");
        }
    }
}
