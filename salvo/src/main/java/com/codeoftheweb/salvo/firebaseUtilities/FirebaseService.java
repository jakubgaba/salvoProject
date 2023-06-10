package com.codeoftheweb.salvo.firebaseUtilities;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseService {


    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference gamesRef;

    @Autowired
    public FirebaseService(FirebaseApp app) {
        this.firebaseDatabase = FirebaseDatabase.getInstance(app);
        this.gamesRef = firebaseDatabase.getReference("gamesSheet");
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public void writeGames(List<Object> games) {
        for (Object game : games) {
            gamesRef.push().setValueAsync(game);
        }
    }
}
