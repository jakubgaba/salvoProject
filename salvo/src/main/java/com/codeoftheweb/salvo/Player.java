package com.codeoftheweb.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity  //The annotation @Entity tells Spring to create a person table for this class.
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long userId;
    @Column(unique = true)
    private String userName;
    //The annotation @Id says that the id instance variable holds the database key for this class.
    //The @Id annotation says this field has a unique value for every instance of Person.
    //The database will create an index will be used of all IDs, for fast retrieval by ID in the future.
    //If you change data in a Person object and save it, the ID will be used to determine what gets replaced in the database.
    //Placing @Id on the private instance variable tells JPA to persist all other instance variables as columns in the database.
    //It doesn't matter that fields are marked private.
    //If there are fields that should not be saved, e.g., because they hold temporary scratch data, annotate them with @Transient.
    // The annotations @GeneratedValue and @GenericGenerator tell JPA to use whatever ID generator is provided by the database system.
    //IDs are generated the first time an instance of Person is saved.
    //The Id will not be correct until then.
    //JPA will create column names based on the field names. You must avoid using names on this merged list of SQL reserved words.
    // There are different strategies for mapping names. Camelcase names, such as currentDate, might translate into current_date for SQL, which is a reserved word.


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_authorities",
            joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "authority")
    private Set<String> authorities = new HashSet<>();

    public Player(){

    }

    public Player(String name, String password, String auth){
        this.userName = name;
        this.password = password;
        this.authorities.add(auth);
    }



    @OneToMany(mappedBy="players")
    private Set<GamePlayer> gamePlayers = new HashSet<GamePlayer>();

    @OneToMany(mappedBy = "playerScore")
    private Set<Score> scoresPlayer = new HashSet<>();

    private String password;

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayers(this);
        gamePlayers.add(gamePlayer);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
    @JsonIgnore
    public Set<Score> getScoresPlayer() {
        return scoresPlayer;
    }

    public void setScoresPlayer(Set<Score> scoresPlayer) {
        this.scoresPlayer = scoresPlayer;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public String getUserName() {
        return userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public boolean checkPassword(String password) {
        return password != null && password.equals(this.password);
    }

    public Map<String, Object> toFirebaseObject() {
        Map<String, Object> firebaseObject = new HashMap<>();

        firebaseObject.put("userId", this.userId);
        firebaseObject.put("userName", this.userName);
        firebaseObject.put("password", this.password);
        firebaseObject.put("authorities", new ArrayList<>(this.authorities));
        firebaseObject.put("gamePlayers", new ArrayList<>(this.gamePlayers));
        firebaseObject.put("scoresPlayer", new ArrayList<>(this.scoresPlayer));

        return firebaseObject;
    }

    @Override
    public String toString() {
        return "Player{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", authorities=" + authorities +
                ", gamePlayers=" + gamePlayers +
                ", scoresPlayer=" + scoresPlayer +
                ", password='" + password + '\'' +
                '}';
    }
}
