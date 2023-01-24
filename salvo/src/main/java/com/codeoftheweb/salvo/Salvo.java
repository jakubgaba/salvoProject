package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long salvoId;

    @ElementCollection(targetClass= String.class)
    private Map<Integer,String> salvoLocation;


    @ManyToOne
    @JoinColumn(name="gamePlaySalvo")
    private GamePlayer gamePlayerSalvo;

    public Salvo(){
    }
    public Salvo(GamePlayer gamePlayerSalvo, Map<Integer,String> salvoLocation){
        this.gamePlayerSalvo=gamePlayerSalvo;
        this.salvoLocation=salvoLocation;
    }

    public long getSalvoId() {
        return salvoId;
    }

    public void setSalvoId(long salvoId) {
        this.salvoId = salvoId;
    }

    public Map<Integer, String> getSalvoLocation() {
        return salvoLocation;
    }

    public void setSalvoLocation(Map<Integer, String> salvoLocation) {
        this.salvoLocation = salvoLocation;
    }

    @JsonIgnore
    public GamePlayer getGamePlayerSalvo() {
        return gamePlayerSalvo;
    }

    public void setGamePlayerSalvo(GamePlayer gamePlayerSalvo) {
        this.gamePlayerSalvo = gamePlayerSalvo;
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
