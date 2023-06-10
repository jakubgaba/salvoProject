package com.codeoftheweb.salvo.FirebaseRepositories;


import com.codeoftheweb.salvo.GamePlayer;
import com.google.firebase.database.*;
import com.google.firebase.FirebaseApp;
import org.springframework.stereotype.Component;
@Component
public class FirebaseGamePlayerRepository {

    private final FirebaseDatabase database;

    public FirebaseGamePlayerRepository(FirebaseApp app) {
        this.database = FirebaseDatabase.getInstance(app);
    }

    public void save(GamePlayer gamePlayer) {
        DatabaseReference gamesRef = database.getReference("gameplayers");
        DatabaseReference newGameRef = gamesRef.push();
        newGameRef.setValueAsync(gamePlayer.toFirebaseObject());
    }
}