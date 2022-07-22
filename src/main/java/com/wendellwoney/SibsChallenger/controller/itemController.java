package com.wendellwoney.SibsChallenger.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.path}")
public class itemController {

    private static final Logger logger = LogManager.getLogger(itemController.class);

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> hello() {
        logger.error("Hey, This is a error!");
        return ResponseEntity.ok("Teste");
    }
}
