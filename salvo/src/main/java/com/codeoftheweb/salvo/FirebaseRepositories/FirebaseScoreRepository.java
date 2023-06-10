package com.codeoftheweb.salvo.FirebaseRepositories;

import com.codeoftheweb.salvo.Score;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Component;

@Component
public class FirebaseScoreRepository {

    private final FirebaseDatabase database;

    public FirebaseScoreRepository(FirebaseApp app) {
        this.database = FirebaseDatabase.getInstance(app);
    }

    public void save(Score score) {
        DatabaseReference scoreRef = database.getReference("scores");
        DatabaseReference newscoreRef = scoreRef.push();
        newscoreRef.setValueAsync(score.toFirebaseObject());
    }
}