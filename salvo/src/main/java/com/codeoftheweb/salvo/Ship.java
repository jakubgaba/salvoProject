package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ship {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long shipId;

    private String shipType;

    @OneToMany(mappedBy="ships")
    private Set<GamePlayer> gamePlayers = new HashSet<GamePlayer>();

    public void addShip(GamePlayer gamePlayer){
        gamePlayer.setShips(this);
        gamePlayers.add(gamePlayer);
    }

    public Ship(){}

    public Ship(String shipType){
        this.shipType = shipType;
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipId=" + shipId +
                ", shipType='" + shipType + '\'' +
                ", gamePlayers=" + gamePlayers +
                '}';
    }
}
