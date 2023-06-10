package com.codeoftheweb.salvo.firebaseUtilities;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebasePlayerRepository;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseSalvoRepository;
import com.codeoftheweb.salvo.Salvo;
import com.codeoftheweb.salvo.SalvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalvoService {

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private FirebaseSalvoRepository firebaseSalvoRepository;

    public void saveToBoth(Salvo salvo) {
        salvoRepository.save(salvo);  // Save to the JPA repository
        firebaseSalvoRepository.save(salvo);  // Save to Firebase
    }
}