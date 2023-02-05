package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation indicates that this class is a controller for handling web requests
public class AppController {

    @Autowired // This annotation tells Spring to automatically wire the PlayerRepository bean into this property
    private PlayerRepository playerRepository;
}
