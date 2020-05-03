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

package com.vartmp7.stalker.ui.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.component.NotLogged;
import com.vartmp7.stalker.datamodel.Organization;
import com.vartmp7.stalker.repository.OrganizationsRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class FavoritesViewModel extends ViewModel {
    public static final String TAG = "com.vartmp7.stalker.ui.preferiti.PreferitiViewModel";
    private OrganizationsRepository orgRepo;

    private LiveData<List<Long>> mutableliveDataOrgIds;
    private LiveData<List<Organization>> liveDataOrganizzazioni;
    @Getter(AccessLevel.PUBLIC)
    private MediatorLiveData<List<Organization>> organizzazioni;
    private MutableLiveData<Boolean> organizationsQueryExhausted;
    private MutableLiveData<Boolean> firebaseQueryExhausted;
    private final Observer<Boolean> queryExhaustedObserver;
    private final Observer<List<Organization>> storageObserver;
    private final Observer<List<Long>> firebaseObserver;

    public FavoritesViewModel() {
        queryExhaustedObserver = aBoolean -> {
//            Log.e(TAG, "cambiamento");
            if (firebaseQueryExhausted.getValue() && organizationsQueryExhausted.getValue()) {
//                Log.d(TAG, "onChanged: if sotto cambiamento");
                final List<Long> orgIds = mutableliveDataOrgIds.getValue();
                List<Organization> list = liveDataOrganizzazioni.getValue()
                        .stream()
                        .filter(o -> orgIds.contains(o.getId()))
                        .collect(Collectors.toList());
                list.forEach(o-> orgRepo.updateOrganization(o.setFavorite(true)));
//                Log.i(TAG, "onChanged: " + list);
                organizzazioni.postValue(list);
//                organizzazioni.removeSource(liveDataOrganizzazioni);
//                organizzazioni.removeSource(mutableliveDataOrgIds);
                organizzazioni.removeSource(organizationsQueryExhausted);
                organizzazioni.removeSource(firebaseQueryExhausted);

            }
        };

        storageObserver = organizzazioni -> {
            organizationsQueryExhausted.setValue(true);

        };
        firebaseObserver = organizzazioni -> {
            firebaseQueryExhausted.setValue(true);

        };
    }


    public void updateOrganizzazione(Organization org) {
        orgRepo.updateOrganization(org);
    }

    public void init(@NotNull OrganizationsRepository orgRepo) {
        this.organizzazioni = new MediatorLiveData<>();
        this.organizzazioni.setValue(orgRepo.getOrganizations().getValue());
        this.orgRepo = orgRepo;

        //this.liveDataOrganizzazioni = orgRepo.getOrganizzazioni();
        //this.mutableliveDataOrgIds = orgRepo.getPreferiti();
        this.firebaseQueryExhausted = new MutableLiveData<>(false);
        this.organizationsQueryExhausted = new MutableLiveData<>(false);
        refresh();
    }

    public void refresh() {
//        Log.e(TAG, "refresh: chiamato");
        int i = 0;
        //MutableLiveData<List<Organizzazione>> listOrgs= new MutableLiveData<>();
        this.firebaseQueryExhausted.setValue(false);
        this.organizationsQueryExhausted.setValue(false);

        this.liveDataOrganizzazioni = orgRepo.getOrganizations();
        this.mutableliveDataOrgIds = orgRepo.getFavorites();

        this.organizzazioni.addSource(liveDataOrganizzazioni, storageObserver);
        this.organizzazioni.addSource(mutableliveDataOrgIds,firebaseObserver);
        //orgRepo.refreshOrganizzazioni();




        //listOrgs.setValue(Arrays.asList(new Organizzazione().setId(1212)));



        this.organizzazioni.addSource(organizationsQueryExhausted, queryExhaustedObserver);
        this.organizzazioni.addSource(firebaseQueryExhausted, queryExhaustedObserver);
    }

    public void removeFromPreferiti(Organization org) throws NotLogged {
        if(organizzazioni.getValue()!=null)
            organizzazioni.postValue(
                organizzazioni.getValue()
                    .stream()
                    .filter(o->o.getId()!=org.getId())
                    .collect(Collectors.toList())
            );
        orgRepo.removeFavorite(org);
    }

}