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

    @Autowired
    private ScoreRepository scoreRepository;

    @RequestMapping("/games")
    public List<Object> getGames() {
        return gameRepository.findAll().stream().map(this::getIndividualGameData).collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{GPId}")
    public Map<String, Object> getGamePlayerByIds(@PathVariable Long GPId){
        return getGameViewData(GPId);
    }




    @RequestMapping("/leaderboard")
    public Map<String, Object> getPlayerLeaders(){
        List<Score> Scores = scoreRepository.findAll();
        Map<String, Object> mapping = new LinkedHashMap<>();
        // I create a Stream from Scores list then we can call sorted method to sort the scores ( It let us process elements in functional way )
        // Comparator takes a F"unction" that will take a !! KEY !! for comparising and returns new Comparator that comapres objects based on the extracted KEY...
        // .reversed() because descending order.
        // .thenComparing takes secondary ! KEY ! when the two object have the same value for the primary key
        // and DONE ! :-}

        List<Score> sortedScores = Scores.stream()
                .sorted(Comparator.comparing(Score::getScore).reversed()
                        .thenComparing(element -> element.getPlayerScore().getUserName()))
                .collect(Collectors.toList());

        mapping.put("Scores", sortedScores.stream().map(Score::getScore));
        mapping.put("PlayersByScore", sortedScores.stream()
                .map(element -> element.getPlayerScore().getUserName()));
        return mapping;
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
        mapping.put("Score", gamePlayer.getPlayers().getScoresPlayer().stream().map(Score::getScore));
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
