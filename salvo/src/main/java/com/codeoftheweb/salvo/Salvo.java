package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//The @Entity annotation is used to define a class as an entity bean.
//This class will be mapped to a database table named Salvo
@Entity
public class Salvo {
    //The @Id annotation is used to define the primary key for an entity.
    //The @GeneratedValue annotation is used to define the primary key generation strategy.
    //In this case, the primary key will be generated automatically using the "native" generation strategy.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long salvoId;

    //The @ElementCollection annotation is used to define a collection of basic type.
    //In this case, the collection is a map of integers to strings.
    //The targetClass attribute specifies that the collection should be of the String class.
    @ElementCollection(targetClass= String.class)
    private List<String> salvoLocation;

    private int roundNumber;

    //The @ManyToOne annotation is used to define a many-to-one relationship between entities.
    //In this case, the Salvo entity has a many-to-one relationship with the GamePlayer entity.
    //The @JoinColumn annotation is used to define the foreign key column for the relationship.
    @ManyToOne
    @JoinColumn(name="gamePlaySalvo")
    private GamePlayer gamePlayerSalvo;

    //default constructor
    public Salvo(){
    }
    //constructor with parameters to define a gamePlayerSalvo and a salvoLocation
    public Salvo(GamePlayer gamePlayerSalvo, List<String> salvoLocation,  int roundNumber){
        this.gamePlayerSalvo=gamePlayerSalvo;
        this.salvoLocation=salvoLocation;
        this.roundNumber = roundNumber;
    }

    //getters and setters for salvoId, salvoLocation and gamePlayerSalvo
    public long getSalvoId() {
        return salvoId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setSalvoId(long salvoId) {
        this.salvoId = salvoId;
    }

    public List<String> getSalvoLocation() {
        return salvoLocation;
    }

    public void setSalvoLocation(List<String> salvoLocation) {
        this.salvoLocation = salvoLocation;
    }

    //The @JsonIgnore annotation is used to prevent the gamePlayerSalvo attribute from being serialized.
    @JsonIgnore
    public GamePlayer getGamePlayerSalvo() {
        return gamePlayerSalvo;
    }

    public void setGamePlayerSalvo(GamePlayer gamePlayerSalvo) {
        this.gamePlayerSalvo = gamePlayerSalvo;
    }


    public Map<String, Object> toFirebaseObject() {
        Map<String, Object> firebaseObject = new HashMap<>();

        firebaseObject.put("salvoId", this.salvoId);
        firebaseObject.put("salvoLocation", this.salvoLocation);
        firebaseObject.put("roundNumber", this.roundNumber);

        return firebaseObject;
    }

    @Override
    public String toString() {
        return "Salvo{" +
                "salvoId=" + salvoId +
                ", salvoLocation=" + salvoLocation +
                ", gamePlayerSalvo=" + gamePlayerSalvo +
                '}';
    }
}
