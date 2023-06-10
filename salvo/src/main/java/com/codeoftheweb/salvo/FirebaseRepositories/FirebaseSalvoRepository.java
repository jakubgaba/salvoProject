package com.codeoftheweb.salvo.FirebaseRepositories;

import com.codeoftheweb.salvo.Game;
import com.codeoftheweb.salvo.Player;
import com.codeoftheweb.salvo.Salvo;
import com.google.firebase.database.*;
import com.google.firebase.FirebaseApp;
import org.springframework.stereotype.Component;
@Component
public class FirebaseSalvoRepository {

    private final FirebaseDatabase database;

    public FirebaseSalvoRepository(FirebaseApp app) {
        this.database = FirebaseDatabase.getInstance(app);
    }

    public void save(Salvo salvo) {
        DatabaseReference salvoRef = database.getReference("salvoes");
        DatabaseReference newsalvoRef = salvoRef.push();
        newsalvoRef.setValueAsync(salvo.toFirebaseObject());
    }
}