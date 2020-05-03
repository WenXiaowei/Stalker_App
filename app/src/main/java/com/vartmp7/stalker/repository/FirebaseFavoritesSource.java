/*
 * MIT License
 *
 * Copyright (c) 2020 VarTmp7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vartmp7.stalker.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


public class FirebaseFavoritesSource implements FavoritesSource {
    public static final String TAG = "com.vartmp7.stalker.repository.FirebaseFavoritesSource";
    private static final String FIELDNAME_ID = "id";
    private static final String FIELDNAME_ORGANIZZAZIONI = "organizzazioni";
    private OrganizationsRepository organizationsRepo;
    private FirebaseFirestore db;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String userId;

    /*
    pescare da firebase e restituire una lista di id che sono i preferiti. (get)
    e dare la possibilita' di dato un id di dire che non è più preferito. dato un id, toglierlo dalla lista
    dei preferiti
    aggiungere un id alla lista dei preferito su firebase firestore
     */
    private MutableLiveData<List<Long>> mutableliveDataOrgIds;
    //private LiveData<List<Organizzazione>> liveDataOrganizzazioni;
   // private MediatorLiveData<List<Organizzazione>> mediatorLiveDataOrganizzazioni;
    //private MutableLiveData<Boolean> organizationsQueryExhausted;
    //private MutableLiveData<Boolean> firebaseQueryExhausted;

    public FirebaseFavoritesSource(String userId, FirebaseFirestore db) {
        this.mutableliveDataOrgIds = new MutableLiveData<>(new ArrayList<>());
        //this.mediatorLiveDataOrganizzazioni = new MediatorLiveData<>();
        //organizationsQueryExhausted = new MutableLiveData<Boolean>(false);
        //firebaseQueryExhausted = new MutableLiveData<Boolean>(false);
        this.userId = userId;
        //this.organizationsRepo = orgRepo;
        this.db = db;
        initUserStorage();
    }

    public void initUserStorage() {
        db.collection("utenti").document(userId)
            .get()
            .addOnSuccessListener(docSnapshot->{
                if(!docSnapshot.exists()){
//                    Log.d(TAG, "initUserStorage(!document...exists()): ");
                    Map<String, Object> userData = new HashMap<>();
                    userData.put(FIELDNAME_ORGANIZZAZIONI, new ArrayList<Long>());
                    db.collection("utenti").document(userId).set(userData);
                }
            }).addOnFailureListener(e->{
//                Log.e(TAG, "initUserStorage: "+e.getMessage());
            });

    }


    @Override
    public void addOrganization(Long orgId) {
        db.collection("utenti").document(userId).
                update(FIELDNAME_ORGANIZZAZIONI, FieldValue.arrayUnion(orgId))
                .addOnSuccessListener(aVoid ->{
                    Log.w(TAG, "organizzazione aggiunta correttamente");
                    final List<Long> ids = mutableliveDataOrgIds.getValue();
                    ids.add(orgId);
                    mutableliveDataOrgIds.postValue(ids);
                })
                .addOnFailureListener(e -> Log.w(TAG, "errore avvenuto aggiungendo organizzazione", e));
    }

/*
    public void updateOrganizzazioni(List<Long> orgIds){
        this.firebaseQueryExhausted.setValue(false);
        this.organizationsQueryExhausted.setValue(false);

        db.collection("utenti").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Map<String, Object> data = documentSnapshot.getData();
                    try {
                        if (data != null) {
                            final List<Long> orgIds = (List<Long>) data.get(FIELDNAME_ORGANIZZAZIONI);
                            Log.w(TAG, "data got from firebase:");
                            orgIds.forEach(l->Log.d(TAG,"orgId:"+l));
                            mutableliveDataOrgIds.postValue(orgIds);
                            firebaseQueryExhausted.postValue(true);
                            //Log.w(TAG, "organizzazioni ottenute correttamente");
                        }
                    } catch (ClassCastException e) {
                    }
                });

        this.mediatorLiveDataOrganizzazioni.addSource(liveDataOrganizzazioni, new Observer<List<Organizzazione>>() {
            @Override
            public void onChanged(List<Organizzazione> organizzazioni) {
               organizationsQueryExhausted.postValue(true);
            }
        });

        this.liveDataOrganizzazioni = new MutableLiveData<>(organizationsRepo.getOrganizzazioni().getValue());

        final Observer<Boolean> queryExhaustedObserver =  new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if(firebaseQueryExhausted.getValue() && organizationsQueryExhausted.getValue()){
                    final List<Long> orgIds = mutableliveDataOrgIds.getValue();
                    mediatorLiveDataOrganizzazioni.postValue(
                            liveDataOrganizzazioni.getValue()
                                    .stream()
                                    .filter(o-> orgIds.contains(o.getId()))
                                    .collect(Collectors.toList())
                    );
                }
            }
        };
        this.mediatorLiveDataOrganizzazioni.addSource(organizationsQueryExhausted, queryExhaustedObserver);
        this.mediatorLiveDataOrganizzazioni.addSource(firebaseQueryExhausted,queryExhaustedObserver);


    }
*/


    @Override
    public void removeOrganization(Long orgId) {
        db.collection("utenti").document(userId).
                update(FIELDNAME_ORGANIZZAZIONI, FieldValue.arrayRemove(orgId))
                .addOnSuccessListener(aVoid ->{
                    final List<Long> ids = mutableliveDataOrgIds.getValue();
                    ids.remove(orgId);
                    mutableliveDataOrgIds.postValue(ids);
                    Log.w(TAG, "organizzazione rimossa correttamente");

                })
                .addOnFailureListener(e -> Log.w(TAG, "errore avvenuto rimuovendo organizzazione", e));
    }



    public LiveData<List<Long>> getFavoriteOrganizationID() {
        db.collection("utenti").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Map<String, Object> data = documentSnapshot.getData();
                    try {
                        if (data != null) {
                            final List<Long> orgIds = (List<Long>) data.get(FIELDNAME_ORGANIZZAZIONI);
//                            Log.w(TAG, "data got from firebase:");
//                            orgIds.forEach(l->Log.d(TAG,"orgId:"+l));
                            mutableliveDataOrgIds.postValue(orgIds);
//                            Log.w(TAG, "id delle org preferite ottenuti correttamente");
                        }
                    } catch (ClassCastException e) {
                    }
                });
        return this.mutableliveDataOrgIds;
    }

    @Override
    public void refresh() {
        db.collection("utenti").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Map<String, Object> data = documentSnapshot.getData();
                    try {
                        if (data != null) {
                            final List<Long> orgIds = (List<Long>) data.get(FIELDNAME_ORGANIZZAZIONI);
//                            Log.w(TAG, "data got from firebase:");
//                            orgIds.forEach(l->Log.d(TAG,"orgId:"+l));
                            mutableliveDataOrgIds.postValue(orgIds);
//                            Log.w(TAG, "id delle org preferite ottenuti correttamente");
                        }
                    } catch (ClassCastException e) {
                    }
                });
    }



}
