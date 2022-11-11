package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long gamePlayerId;

//    private String gameCreation;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game games;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player players;

    public GamePlayer(){}

    public GamePlayer(Game game, Player player){
//        this.gameCreation = gameDate;
        this.games = game;
        this.players = player;
    }

//    public String getGameCreation() {
//        return gameCreation;
//    }
//
//    public void setGameCreation(String gameCreation) {
//        this.gameCreation = gameCreation;
//    }

    public Game getGames() {
        return games;
    }

    public void setGames(Game games) {
        this.games = games;
    }

    public Player getPlayers() {
        return players;
    }

    public void setPlayers(Player players) {
        this.players = players;
    }

    public long getGamePlayerId() {
        return gamePlayerId;
    }

    public void setGamePlayerId(long gamePlayerId) {
        this.gamePlayerId = gamePlayerId;
    }

    @Override
    public String toString() {
        return "GamePlayer{" +
                "gamePlayerId=" + gamePlayerId +
//                ", gameCreation='" + gameCreation + '\'' +
                ", games=" + games +
                ", players=" + players +
                '}';
    }
}
