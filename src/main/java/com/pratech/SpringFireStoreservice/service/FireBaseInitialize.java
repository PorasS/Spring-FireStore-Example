package com.pratech.SpringFireStoreservice.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FireBaseInitialize {

    @Value("${gcp.credential}")
    private String gcpAccessKey;

    @Value("${gcp.dburl}")
    private String dburl;

    //makes to run the method during the startUp
    @PostConstruct
    public void initialize() throws IOException {
        GoogleCredentials credential = GoogleCredentials.fromStream(
                new ByteArrayInputStream(gcpAccessKey.getBytes(StandardCharsets.UTF_8)));

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credential)
                .setDatabaseUrl(dburl)
                .build();

        FirebaseApp app= FirebaseApp.initializeApp(options);
    }
}
