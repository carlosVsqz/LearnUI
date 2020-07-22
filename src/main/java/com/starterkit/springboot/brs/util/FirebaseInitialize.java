package com.starterkit.springboot.brs.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitialize {
    @PostConstruct
    public void initialize() throws IOException {
        String NAME = "src/main/resources/db/serviceAccountKey.json";
        FileInputStream serviceAccount = new FileInputStream(NAME);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://learnui-1f45b.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
    }
}
