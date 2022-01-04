package com.gamelibrary.gamelibraryapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class DeveloperController {

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
}
