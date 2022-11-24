package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/games")
    public List<Object> getGames() {
        return gameRepository.findAll()
                  .stream().map(this::getGamesMapping).collect(Collectors.toList());
    }

//
   public Map<String, Object> getGamesMapping(Game gameEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", gameEach.getGameId());
        mapping.put("created", gameEach.getGameCreated());
        mapping.put("gamePlayers", mapGamePlayers(gameEach.getGamePlayers()));   // now i have [{ .... }]

       return
                mapping;
   }

    public List<Object> mapGamePlayers(Set<GamePlayer> mapGame){
        return mapGame
                .stream().map(this::getGamePlayerEach).collect(Collectors.toList());
    }
    public Map<String, Object> getGamePlayerEach(GamePlayer gamePlayerEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", gamePlayerEach.getGamePlayerId());
        mapping.put("players", getPlayers(gamePlayerEach.getPlayers()));
        return mapping;
    }

public Map<String, Object> getPlayers(Player player){
    Map<String, Object> mapping = new LinkedHashMap<>();
    mapping.put("id", player.getUserId());
    mapping.put("userName", player.getUserName());
    return
            mapping;
}
}
