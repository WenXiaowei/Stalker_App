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

public class FirebaseFavoritesRepository implements FavoritesRepository {
    public static final String TAG ="package com.vartmp7.stalker.component.ProvaPreferitiRepository";
    private static final String FIELDNAME_ID ="id";
    private OrganizationsRepository organizationsRepo;
    private FirebaseFirestore db;
    private String userId;

    public FirebaseFavoritesRepository(String userId, OrganizationsRepository orgRepo,FirebaseFirestore db) {
        this.userId=userId;
        this.organizationsRepo=orgRepo;
        this.db = db;
    }
    public void setUserID(String userId){
        this.userId=userId;
    }

    @Override
    public void addOrganizzazione(Organizzazione organizzazione) {
    //TODO

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
    /* ottiene gli id delle organizzazioni da Cloud Firestore db, poi contatta l'OrganizationsRepository per ottenere tutte le info
        delle organizzazioni, poi costruisce un livedata e lo ritorna.
     */

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
