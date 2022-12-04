package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "gamePlayer")
    private List<Ship> ships;

    public GamePlayer(){}
    public GamePlayer(Game game, Player player){
        this.games = game;
        this.players = player;
    }


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

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
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
