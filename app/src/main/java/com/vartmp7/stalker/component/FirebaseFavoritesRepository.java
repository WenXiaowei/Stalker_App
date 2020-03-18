package com.vartmp7.stalker.component;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FirebaseFavoritesRepository implements FavoritesRepository {
    public static final String TAG ="package com.vartmp7.stalker.component.FirebaseFavoritesRepository";
    private static final String FIELDNAME_ID ="id";
    private static final String FIELDNAME_ORGANIZZAZIONI = "organizzazioni";
    private OrganizationsRepository organizationsRepo;
    private FirebaseFirestore db;
    private String userId;
    private MutableLiveData<List<Organizzazione>> mutableLiveDataOrganizzazioni;

    public FirebaseFavoritesRepository(String userId, OrganizationsRepository orgRepo,FirebaseFirestore db) {
        this.mutableLiveDataOrganizzazioni = new MutableLiveData<>(new ArrayList<Organizzazione>());
        this.userId=userId;
        this.organizationsRepo=orgRepo;
        this.db = db;
    }
    public void setUserID(String userId){
        this.userId=userId;
    }


    public void initUserStorage(String userId){
        Map<String,Object> userData = new HashMap<>();
        userData.put(FIELDNAME_ORGANIZZAZIONI, new ArrayList<Long>());
        db.collection("utenti").document(userId).set(userData);
    }

    @Override
    public void addOrganizzazione(Organizzazione organizzazione) {
    //TODO


        db.collection("utenti").document(userId).
                update(FIELDNAME_ORGANIZZAZIONI, FieldValue.arrayUnion(organizzazione.getId()))
                .addOnSuccessListener(aVoid -> Log.w(TAG,"organizzazione aggiunta correttamente"))
                .addOnFailureListener(e -> Log.w(TAG, "errore avvenuto aggiungendo organizzazione", e));


/*
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
*/
    }

    @Override
    public void removeOrganizzazione(Organizzazione organizzazione) {

        db.collection("utenti").document(userId).
                update(FIELDNAME_ORGANIZZAZIONI, FieldValue.arrayRemove(organizzazione.getId()))
                .addOnSuccessListener(aVoid -> Log.w(TAG,"organizzazione rimossa correttamente"))
                .addOnFailureListener(e -> Log.w(TAG, "errore avvenuto rimuovendo organizzazione", e));

/*
        Map<String, Object> org = new HashMap<>();
        org.put(FIELDNAME_ID,organizzazione.getId());
        // remove a document with a generated ID
        db.collection("utenti").document(userId).collection("organizzazioni")
                .document(""+organizzazione.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w(TAG,"deleted with success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"failure: not deleted");
                    }
                });


 */

    }



    @Override
    public LiveData<List<Organizzazione>> getOrganizzazioni() {
        //chiamata a firebase

        db.collection("utenti").document(userId)
                .get().addOnSuccessListener(documentSnapshot->{
                    Map<String, Object> data = documentSnapshot.getData();
                    try{

                        if(data!=null){
                            final List<Long> organizzazioni= (List<Long>) data.get(FIELDNAME_ORGANIZZAZIONI);
                            Log.w(TAG,organizzazioni.toString());
                            if(organizzazioni!=null){
                                Log.w(TAG,"organizzazioni ottenute correttamente");
                                this.mutableLiveDataOrganizzazioni = new MutableLiveData<>(
                                    organizationsRepo.getOrganizzazioni().getValue()
                                            .stream()
                                            .filter(o->{
                                                    return true;/*
                                                    boolean contained=false;
                                                    for (Long orgId: organizzazioni){
                                                        if(orgId==o.getId()){
                                                            contained=true;
                                                            break;
                                                        }
                                                    }
                                                    return contained;*/
                                            })
                                            .collect(Collectors.toList())
                                );
                                this.mutableLiveDataOrganizzazioni.getValue().forEach(o->Log.w(TAG,""+o.getId()));
                            }else{
                                Log.w(TAG, "errore avvenuto nell'ottenimento delle organizzazioni: problemi con il documento");
                            }

                        }else{
                            Log.w(TAG,"errore avvenuto nell'ottenimento delle organizzazioni: documento non esistente");
                            this.mutableLiveDataOrganizzazioni=new MutableLiveData<>(new ArrayList<Organizzazione>());
                        }




                    }catch(ClassCastException e){
                        Log.e(TAG,e.getMessage());
                    }
                })
                .addOnFailureListener(e->Log.w(TAG, "errore avvenuto nell'ottenimento delle organizzazioni", e));
        return this.mutableLiveDataOrganizzazioni;

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
