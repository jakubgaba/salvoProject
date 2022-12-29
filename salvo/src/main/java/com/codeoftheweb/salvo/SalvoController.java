package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/game_view/{GPId}")
    public Map<String, Object> getGamePlayerByIds(@PathVariable Long GPId){
        return
                gameViewHelpMethod(GPId);
    }

   public Map<String, Object> gameViewHelpMethod(Long GPId){
       Map<String, Object> oneGamePlayer = new LinkedHashMap<>();
       oneGamePlayer.put("gameId", gamePlayerRepository.findById(GPId).get().getGames().getGameId());
       oneGamePlayer.put("gamePlayerId", gamePlayerRepository.findById(GPId).get().getGamePlayerId());
       oneGamePlayer.put("created", gamePlayerRepository.findById(GPId).get().getGames().getGameCreated());
       oneGamePlayer.put("gamePlayers", gamePlayersById(gamePlayerRepository.findById(GPId).get().getGames().getGamePlayers()));
       oneGamePlayer.put("ships", gameShipsById(gamePlayerRepository.findById(GPId).get().getShips()));
       return oneGamePlayer;
   }

public List<Object> gamePlayersById(Set<GamePlayer> mapGame){
    return mapGame
            .stream().map(this::getGamePlayerById).collect(Collectors.toList());
}


    public Map<String, Object> getGamePlayerById(GamePlayer gamePlayerEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("IDontWantItHere", getPlayerById(gamePlayerEach.getPlayers()));
        return mapping;
    }
    public Map<String, Object> getPlayerById(Player player){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", player.getUserId());
        mapping.put("player", player.getUserName());
        return
                mapping;
    }

    public List<Object> gameShipsById(Set<Ship> mapShip){
        return mapShip
                .stream().map(this::getShipById).collect(Collectors.toList());
    }
    public Map<String, Object> getShipById(Ship shipEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("type", shipEach.getShipType());
        mapping.put("locations", shipEach.getShipLocation());
        return
                mapping;
    }
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
