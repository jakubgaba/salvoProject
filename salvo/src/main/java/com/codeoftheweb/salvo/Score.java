package com.codeoftheweb.salvo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long scoreId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game gameScore;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player playerScore;

    private int Score;
    private Date finishDate;
    public Score(){}

    public Score(Integer Score, Player player, Game game){
        this.Score = Score;
        this.playerScore = player;
        this.gameScore= game;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public long getScoreId() {
        return scoreId;
    }

    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }

    public Game getGameScore() {
        return gameScore;
    }

    public void setGameScore(Game gameScore) {
        this.gameScore = gameScore;
    }

    public Player getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(Player playerScore) {
        this.playerScore = playerScore;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Map<String, Object> toFirebaseObject() {
        Map<String, Object> firebaseObject = new HashMap<>();

        firebaseObject.put("scoreId", this.scoreId);
        firebaseObject.put("score", this.Score);
        // If you want to include game and player's id for each score,
        // uncomment the following lines
        // firebaseObject.put("gameId", this.gameScore.getGameId());
        // firebaseObject.put("playerId", this.playerScore.getPlayerId());
        // You might want to convert the finishDate to a String for easier handling
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate;
        if (finishDate != null) {
            formattedDate = format.format(finishDate);
        } else {

            formattedDate = "";
        }

        return firebaseObject;
    }

}
