package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

//This class is a JPA Entities
@Entity
public class Game {

    //The gameId field is the primary key of the entity and it will be generated automatically
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long gameId;
    private String gameCreated;

    //Empty constructor
    public Game(){}

    /**
     * Constructor that takes a single parameter, a string representing the date when the game was created
     * @param gameCreated the date when the game was created
     */
    public Game(String gameCreated){
        this.gameCreated= gameCreated;
    }

    //Getter for the gameCreated field
    public String getGameCreated() {
        return gameCreated;
    }

    //Getter for the gamePlayers field, it will ignore when serializing the object to JSON
    @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    //Setter for the gamePlayers field
    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    //@OneToMany association between Game and GamePlayer entities
    // One game can have multiple gameplayers and it is mapped by the attribute 'games' in the GamePlayer class
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
        // Convert the GamePlayer set to a suitable format if needed
        // firebaseObject.put("gamePlayers", this.convertGamePlayers(this.getGamePlayers()));
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
