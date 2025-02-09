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

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.Observer;
//
//import com.vartmp7.stalker.gsonbeans.Organizzazione;
//import com.vartmp7.stalker.repository.OrganizationsRepository;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(JUnit4.class)
//public class OrganizationsViewModelTest {
//    @Rule
//    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

//    @Mock
//    OrganizationsRepository orgRepo;
//
//    private OrganizationsViewModel orgViewModel;
//    @Mock
//    Observer<List<Organizzazione>> observer;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        orgViewModel = new OrganizationsViewModel();
//        orgViewModel.initData(orgRepo);
//        orgViewModel.getOrganizationList().observeForever(observer);
//    }
//
//    @Test
//    public void shouldfetchData(){
//        when(orgRepo.getOrganizzazioni()).thenReturn(new MutableLiveData<List<Organizzazione>>());
//    }
//
//}
