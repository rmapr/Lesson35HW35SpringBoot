
package com.springboot.lesson35hw35springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class TestOrderController {
    private static final Logger logger = LoggerFactory.getLogger("logger");
    @GetMapping("/ping")
    public String ping() {
        logger.info("Запуск тест кортроллера.");
        return "OK";
    }
}
