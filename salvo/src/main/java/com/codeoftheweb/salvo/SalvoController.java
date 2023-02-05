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

    @Autowired
    private SalvoRepository salvoRepository;

    @RequestMapping("/games")
    public List<Object> getGames() {
        return gameRepository.findAll().stream().map(this::getIndividualGameData).collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{GPId}")
    public Map<String, Object> getGamePlayerByIds(@PathVariable Long GPId){
        return getGameViewData(GPId);
    }

    public Map<String, Object> getGameViewData(Long GPId){
        Map<String, Object> oneGamePlayer = new LinkedHashMap<>();

        GamePlayer gamePlayer = gamePlayerRepository.findById(GPId).get();

        oneGamePlayer.put("gameId", gamePlayer.getGames().getGameId());
        oneGamePlayer.put("gamePlayerId", gamePlayer.getGamePlayerId());
        oneGamePlayer.put("created", gamePlayer.getGames().getGameCreated());
        oneGamePlayer.put("gamePlayers", getGamePlayersData(gamePlayer.getGames().getGamePlayers(), GPId));
        oneGamePlayer.put("ships", getShipsData(gamePlayer.getShips()));
        oneGamePlayer.put("salvoes", salvoRepository.findById(GPId).get().getSalvoLocation());

        return oneGamePlayer;
    }

    public List<Object> getGamePlayersData(Set<GamePlayer> gamePlayers, Long GPId){
        return gamePlayers.stream().map(gamePlayer -> getIndividualGamePlayerData(gamePlayer, GPId)).collect(Collectors.toList());
    }

    public Map<String, Object> getIndividualGamePlayerData(GamePlayer gamePlayer, Long GPId){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("Id", gamePlayer.getPlayers().getUserId());
        mapping.put("Player", gamePlayer.getPlayers().getUserName());

        if(gamePlayer.getPlayers().getUserId() != GPId){
            mapping.put("enemyShipLocations", gamePlayer.getShips().stream().map(Ship::getShipLocation));
            mapping.put("enemySalvoes", gamePlayer.getSalvos().stream().map(Salvo::getSalvoLocation));
        }

        return mapping;
    }

    public List<Object> getShipsData(Set<Ship> ships){
        return ships.stream().map(this::getIndividualShipData).collect(Collectors.toList());
    }

    public Map<String, Object> getIndividualShipData(Ship ship){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("type", ship.getShipType());
        mapping.put("locations", ship.getShipLocation());
        return mapping;
    }
   public Map<String, Object> getIndividualGameData(Game gameEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", gameEach.getGameId());
        mapping.put("created", gameEach.getGameCreated());
        mapping.put("gamePlayers", getGamePlayersInAGameData(gameEach.getGamePlayers()));   // now i have [{ .... }]
       return
                mapping;
   }
    public List<Object> getGamePlayersInAGameData(Set<GamePlayer> mapGame){
        return mapGame
                .stream().map(this::getIndividualGamePlayerInAGameData).collect(Collectors.toList());
    }
    public Map<String, Object> getIndividualGamePlayerInAGameData(GamePlayer gamePlayerEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", gamePlayerEach.getGamePlayerId());
        mapping.put("players",  getPlayerData(gamePlayerEach.getPlayers()));
        return mapping;
    }

public Map<String, Object>  getPlayerData(Player player){
    Map<String, Object> mapping = new LinkedHashMap<>();
    mapping.put("id", player.getUserId());
    mapping.put("userName", player.getUserName());
    return
            mapping;
}
}
