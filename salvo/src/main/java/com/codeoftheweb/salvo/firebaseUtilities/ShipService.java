package com.codeoftheweb.salvo.firebaseUtilities;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseScoreRepository;
import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseShipRepository;
import com.codeoftheweb.salvo.Score;
import com.codeoftheweb.salvo.ScoreRepository;
import com.codeoftheweb.salvo.Ship;
import com.codeoftheweb.salvo.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private FirebaseShipRepository firebaseShipRepository;

    public void saveToBoth(Ship ship) {
        shipRepository.save(ship);  // Save to the JPA repository
        firebaseShipRepository.save(ship);  // Save to Firebase
    }
}