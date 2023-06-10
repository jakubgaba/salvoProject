package com.codeoftheweb.salvo.firebaseUtilities;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseGamePlayerRepository;
import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseGameRepository;
import com.codeoftheweb.salvo.Game;
import com.codeoftheweb.salvo.GamePlayer;
import com.codeoftheweb.salvo.GamePlayerRepository;
import com.codeoftheweb.salvo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerService {

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private FirebaseGamePlayerRepository firebaseGamePlayerRepository;

    public void saveToBoth(GamePlayer gamePlayer) {
        gamePlayerRepository.save(gamePlayer);  // Save to the JPA repository
        firebaseGamePlayerRepository.save(gamePlayer);  // Save to Firebase
    }
}