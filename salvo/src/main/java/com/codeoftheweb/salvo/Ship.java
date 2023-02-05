package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity represent the objects that we want to persist in the database.
 * This Entity class is used to define the ships that are going to be used in the game.
 */
@Entity
public class Ship {

    /**
     * The shipId field is used to identify the ship uniquely in the database.
     * It is generated automatically by the database and is of type long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long shipId;

    /**
     * The shipType field is used to store the type of ship.
     * It is of type String.
     */
    private String shipType;

    /**
     * The shipLocation field is used to store the location of the ship.
     * It is a list of strings.
     */
    @ElementCollection(targetClass= String.class)
    private List<String> shipLocation;

    /**
     * The gamePlayer field is used to store the gamePlayer to which the ship belongs.
     * It is a ManyToOne relationship with the GamePlayer class.
     */
    @ManyToOne
    @JoinColumn(name="gamePlayer")
    private GamePlayer gamePlayer;

    /**
     * A default constructor is created to instantiate the class
     */
    public Ship(){}

    /**
     * A constructor is created to instantiate the class with parameters.
     *
     * @param shipType The type of the ship
     * @param gamePlayer The gamePlayer to which the ship belongs
     * @param shipLocation The location of the ship
     */
    public Ship(String shipType, GamePlayer gamePlayer, List<String> shipLocation){
        this.shipType = shipType;
        this.gamePlayer = gamePlayer;
        this.shipLocation = shipLocation;
    }

    /**
     * Getter method for shipId
     * @return The Id of the ship
     */
    public long getShipId() {
        return shipId;
    }

    /**
     * Setter method for shipId
     * @param shipId The Id of the ship
     */
    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    /**
     * Getter method for shipType
     * @return The type of the ship
     */
    public String getShipType() {
        return shipType;
    }

    /**
     * Getter method for shipLocation
     * @return The location of the ship
     */
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
