package com.vartmp7.stalker.component;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class FirebasePreferitiRepository implements PreferitiRepository {
    public static final String TAG ="package com.vartmp7.stalker.component.ProvaPreferitiRepository";
    private static final String FIELDNAME_ID ="id";

    private FirebaseFirestore db;
    private String userId;

    public FirebasePreferitiRepository(String userId, FirebaseFirestore db) {
        this.userId=userId;
        this.db = db;
    }
    public void setUserID(String userId){
        this.userId=userId;
    }

    @Override
    public void addOrganizzazione(Organizzazione organizzazione) {
    //TODO
        // Create a new user with a first, middle, and last name
        Map<String, Object> org = new HashMap<>();
        org.put(FIELDNAME_ID,organizzazione.getId());


        // Add a new document with a generated ID
        db.collection("utenti").document(userId).collection("organizzazioni")
                .add(org)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    @Override
    public void removeOrganizzazione(Organizzazione organizzazione) {
    //TODO
    }

    @Override
    public LiveData<List<Organizzazione>> getOrganizzazioni() {
    //TODO
    // ottiene le organizzazioni da Cloud Firestore db e le restituisce sottoforma di liveData
        return null;
    }

    public void addStupidText(String stupidTextkey, String stupidTextValue){
        // Create a new user with a first, middle, and last name
        Map<String, Object> stupidText = new HashMap<>();
        stupidText.put(stupidTextkey, stupidTextValue);


        // Add a new document with a generated ID
        db.collection("stupidTexts")
                .add(stupidText)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }





}
