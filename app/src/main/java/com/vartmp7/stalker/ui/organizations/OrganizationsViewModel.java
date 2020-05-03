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

package com.vartmp7.stalker.ui.organizations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vartmp7.stalker.datamodel.Organization;
import com.vartmp7.stalker.repository.OrganizationsRepository;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class OrganizationsViewModel extends ViewModel {
    public static final String TAG = "com.vartmp7.stalker.ui.organizations.OrganizationsViewModel";

    private OrganizationsRepository orgRepo;
    @Getter(AccessLevel.PUBLIC)
    private LiveData<List<Organization>> organizationList;


    public void initData(OrganizationsRepository orgRepo) {
        if (organizationList != null) {
            return;
        }
        this.orgRepo = orgRepo;
        //TODO togliere questo casting
        this.organizationList = orgRepo.getOrganizations();
    }


    public void updateOrganizzazione(Organization o){
        orgRepo.updateOrganization(o);
    }

   /* public void updateData() {
        orgRepo.updateOrganizzazioni();
    }*/


//    public void aggiungiOrganizzazione(final Organizzazione org) {
//        List<Organizzazione> l = organizationList.getValue();
//        if (l==null)
//            l= new ArrayList<>();
//        l.add(org);
//        organizationList.setValue(l);
//    }

    public void refresh() {
        orgRepo.refreshOrganizations();
    }
}