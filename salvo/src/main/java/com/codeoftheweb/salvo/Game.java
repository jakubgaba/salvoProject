package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

//This class is a JPA Entities
@Entity
public class Game {

    //The gameId field is the primary key of the entity and it will be generated automatically
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long gameId;
    private String gameCreated;


    public Game(){}

    public Game(String gameCreated){
        this.gameCreated= gameCreated;
    }


    public String getGameCreated() {
        return gameCreated;
    }


    @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }


    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    @OneToMany(mappedBy="games")
    private Set<GamePlayer> gamePlayers = new HashSet<GamePlayer>();

    @OneToMany(mappedBy = "gameScore")
    private Set<Score> scoresGame = new HashSet<>();

    @JsonIgnore
    public Set<Score> getScoresGame() {
        return scoresGame;
    }

    public void setScoresGame(Set<Score> scoresGame) {
        this.scoresGame = scoresGame;
    }


    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }


    public void setGameCreated(String gameCreated) {
        this.gameCreated = gameCreated;
    }


    public Map<String, Object> toFirebaseObject() {
        Map<String, Object> firebaseObject = new HashMap<>();
        firebaseObject.put("id", this.getGameId());
        firebaseObject.put("created", this.getGameCreated());
        return firebaseObject;
    }
    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", gameCreated='" + gameCreated + '\'' +
                '}';
    }
}
