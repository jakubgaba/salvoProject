package com.codeoftheweb.salvo.firebaseUtilities;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebasePlayerRepository;
import com.codeoftheweb.salvo.Game;
import com.codeoftheweb.salvo.GameRepository;
import com.codeoftheweb.salvo.Player;
import com.codeoftheweb.salvo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private FirebasePlayerRepository firebasePlayerRepository;

    public void saveToBoth(Player player) {
        playerRepository.save(player);  // Save to the JPA repository
        firebasePlayerRepository.save(player);  // Save to Firebase
    }
}
