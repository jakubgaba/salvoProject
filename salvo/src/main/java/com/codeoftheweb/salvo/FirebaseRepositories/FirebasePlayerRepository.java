package com.codeoftheweb.salvo.FirebaseRepositories;

import com.codeoftheweb.salvo.Game;
import com.codeoftheweb.salvo.Player;
import com.google.firebase.database.*;
import com.google.firebase.FirebaseApp;
import org.springframework.stereotype.Component;
@Component
public class FirebasePlayerRepository {

    private final FirebaseDatabase database;

    public FirebasePlayerRepository(FirebaseApp app) {
        this.database = FirebaseDatabase.getInstance(app);
    }

    public void save(Player player) {
        DatabaseReference playersRef = database.getReference("players");
        DatabaseReference newPlayersRef = playersRef.push();
        newPlayersRef.setValueAsync(player.toFirebaseObject());
    }
}