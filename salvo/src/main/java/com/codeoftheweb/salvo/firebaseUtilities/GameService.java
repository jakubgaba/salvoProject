package com.codeoftheweb.salvo.firebaseUtilities;
import com.codeoftheweb.salvo.Game;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseGameRepository;

import com.codeoftheweb.salvo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private FirebaseGameRepository firebaseGameRepository;

    public void saveToBoth(Game game) {
        gameRepository.save(game);  // Save to the JPA repository
        firebaseGameRepository.save(game);  // Save to Firebase
    }
}
