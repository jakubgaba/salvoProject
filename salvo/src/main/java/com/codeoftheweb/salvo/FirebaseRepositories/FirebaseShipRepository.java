package com.codeoftheweb.salvo.FirebaseRepositories;

import com.codeoftheweb.salvo.Game;
import com.codeoftheweb.salvo.Player;
import com.codeoftheweb.salvo.Ship;
import com.google.firebase.database.*;
import com.google.firebase.FirebaseApp;
import org.springframework.stereotype.Component;
@Component
public class FirebaseShipRepository {

    private final FirebaseDatabase database;

    public FirebaseShipRepository(FirebaseApp app) {
        this.database = FirebaseDatabase.getInstance(app);
    }

    public void save(Ship ship) {
        DatabaseReference shipsRef = database.getReference("ships");
        DatabaseReference newShipsRef = shipsRef.push();
        newShipsRef.setValueAsync(ship.toFirebaseObject());
    }
}