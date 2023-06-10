package com.codeoftheweb.salvo.FirebaseRepositories;

import com.codeoftheweb.salvo.Game;
import com.google.firebase.database.*;
import com.google.firebase.FirebaseApp;
import org.springframework.stereotype.Component;

@Component
public class FirebaseGameRepository {

    private final FirebaseDatabase database;

    public FirebaseGameRepository(FirebaseApp app) {
        this.database = FirebaseDatabase.getInstance(app);
    }

    public void save(Game game) {
        DatabaseReference gamesRef = database.getReference("games");
        DatabaseReference newGameRef = gamesRef.push();
        newGameRef.setValueAsync(game.toFirebaseObject());
    }
}