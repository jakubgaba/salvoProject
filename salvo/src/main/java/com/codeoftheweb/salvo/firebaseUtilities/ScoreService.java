package com.codeoftheweb.salvo.firebaseUtilities;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseSalvoRepository;
import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseScoreRepository;
import com.codeoftheweb.salvo.Salvo;
import com.codeoftheweb.salvo.SalvoRepository;
import com.codeoftheweb.salvo.Score;
import com.codeoftheweb.salvo.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private FirebaseScoreRepository firebaseScoreRepository;

    public void saveToBoth(Score score) {
        scoreRepository.save(score);  // Save to the JPA repository
        firebaseScoreRepository.save(score);  // Save to Firebase
    }
}