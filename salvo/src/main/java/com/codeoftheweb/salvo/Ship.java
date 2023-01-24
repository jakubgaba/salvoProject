package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Ship {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long shipId;

    private String shipType;

    @ElementCollection(targetClass= String.class)
    private List<String> shipLocation;
    @ManyToOne
    @JoinColumn(name="gamePlayer")
    private GamePlayer gamePlayer;


    public Ship(){}

    public Ship(String shipType, GamePlayer gamePlayer, List<String> shipLocation){
        this.shipType = shipType;
        this.gamePlayer = gamePlayer;
        this.shipLocation = shipLocation;
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

    public List<String> getShipLocation() {
        return shipLocation;
    }

    public void setShipLocation(List<String> shipLocation) {
        this.shipLocation = shipLocation;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayers) {
        this.gamePlayer = gamePlayers;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipId=" + shipId +
                ", shipType='" + shipType + '\'' +
                ", gamePlayers=" + gamePlayer +
                '}';
    }
}
