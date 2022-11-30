package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long gameId;
    private String gameCreated;

    public Game(){}
    public Game(String game){

        this.gameCreated= game;
    }

    public String getGameCreated() {
        return gameCreated;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    @OneToMany(mappedBy="games")
    private Set<GamePlayer> gamePlayers = new HashSet<GamePlayer>();

    public void addGamePlayers(GamePlayer gamePlayer) {
        gamePlayer.setGames(this);
        gamePlayers.add(gamePlayer);
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

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", gameCreated='" + gameCreated + '\'' +
                '}';
    }
}
