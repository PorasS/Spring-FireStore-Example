package com.pratech.SpringFireStoreservice.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.pratech.SpringFireStoreservice.model.Collection;
import com.pratech.SpringFireStoreservice.model.Student;
import com.pratech.SpringFireStoreservice.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FireBaseService {
    public static final Logger logger = LoggerFactory.getLogger(FireBaseService.class);
    public String saveUserDetails(Users user) throws ExecutionException, InterruptedException {
       Firestore dbFirestore= FirestoreClient.getFirestore();
       ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document().set(user);
       return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Student getStudentDetails() throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();

        DocumentReference docRef= dbFirestore.collection("student").document("VRgz3KEi2oKlRufCyKBV");
        ApiFuture<DocumentSnapshot> docSnapshot = docRef.get();
        DocumentSnapshot documentSnapshot=docSnapshot.get();
        Student stud=null;
        if(documentSnapshot.exists()){
            stud = documentSnapshot.toObject(Student.class);
            return stud;
        }else{
            return null;
        }
    }

    public List<Student>  getStudentCollection() throws ExecutionException, InterruptedException {
        Firestore fdb= FirestoreClient.getFirestore();
        CollectionReference collectionReference = fdb.collection("student");
        ApiFuture<QuerySnapshot> querySnapshotApiFuture=collectionReference.get();
        QuerySnapshot querySnapshot= querySnapshotApiFuture.get();
        List<Student> studentList=querySnapshot.toObjects(Student.class);
        return studentList;

    }

    public List<Collection> getCollections() throws ExecutionException, InterruptedException {
        Firestore fdb= FirestoreClient.getFirestore();
        //get the collection refrence
        CollectionReference collectionReference = fdb.collection("collections");
        //get the collectionSnapShot
        ApiFuture<QuerySnapshot> querySnapshotApiFuture=collectionReference.get();
        QuerySnapshot querySnapshot= querySnapshotApiFuture.get();
        List<Collection> collection=querySnapshot.toObjects(Collection.class);
        return collection;
    }

}
