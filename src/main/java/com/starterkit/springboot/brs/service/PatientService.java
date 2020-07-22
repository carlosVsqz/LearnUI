package com.starterkit.springboot.brs.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.starterkit.springboot.brs.pojo.Patient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class PatientService{
    private static final String COL_NAME="users";


    public String savePatientDetails(Patient patient) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(COL_NAME).document(patient.getName()).set(patient);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Patient getPatientDetails(String name) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> snapshotApiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = snapshotApiFuture.get();
        Patient patient = null;
        if (documentSnapshot.exists()){
            patient = documentSnapshot.toObject(Patient.class);
            return patient;
        }else return null;
    }
    public String updatePatientDetails(Patient patient) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApiFuture = firestore.collection(COL_NAME).document(patient.getName()).set(patient);
        return writeResultApiFuture.get().getUpdateTime().toString();
    }

    public String deletePatientDetails(String name){
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApiFuture = firestore.collection(COL_NAME).document(name).delete();
        return "Document with Patient ID "+name+" has been deleted";
    }
}
