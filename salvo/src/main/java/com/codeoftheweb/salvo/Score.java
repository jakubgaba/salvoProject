package com.codeoftheweb.salvo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


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
}
