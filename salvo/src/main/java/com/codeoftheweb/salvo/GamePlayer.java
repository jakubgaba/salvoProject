package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long gamePlayerId;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game games; //Many GamePlayer entities are associated with one Game entity.

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player players; //Many GamePlayer entities are associated with one Player entity.

    @OneToMany(mappedBy = "gamePlayerSalvo")
    private Set<Salvo> salvos; //One GamePlayer entity can have many Salvo entities associated with it.

    @OneToMany(mappedBy = "gamePlayer")
    private Set<Ship> ships; //One GamePlayer entity can have many Ship entities associated with it.

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

    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public void setSalvos(Set<Salvo> salvos) {
        this.salvos = salvos;
    }


    @Override
    public String toString() {
        return "GamePlayer{" +
                "salvos=" + salvos +
                '}';
    }
}