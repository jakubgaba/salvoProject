package com.codeoftheweb.salvo.utilities;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

import com.google.firebase.FirebaseApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FirebaseTest {

    private final FirebaseApp firebaseApp;

    @Autowired
    public FirebaseTest(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }

    @PostConstruct
    public void writeAndReadValue() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(firebaseApp);
        DatabaseReference ref = database.getReference().child("test");
        ref.setValue("What the heck!", (error, ref1) -> {
            if (error != null) {
                System.out.println("Write operation failed: " + error.getMessage());
            } else {
                System.out.println("Write operation successful.");
                readValue(ref);
            }
        });
    }

    private void readValue(DatabaseReference ref) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object value = dataSnapshot.getValue();
                System.out.println("Read operation successful. Value: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Read operation cancelled: " + error.getMessage());
            }
        });
    }
}
