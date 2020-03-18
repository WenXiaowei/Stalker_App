package com.vartmp7.stalker.component;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.component.gsonbeans.Organizzazione;
import com.vartmp7.stalker.component.gsonbeans.ResponseLuogo;
import com.vartmp7.stalker.component.gsonbeans.ResponseOrganizzazione;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RESTOrganizationsRepository implements OrganizationsRepository {
    private static final String TAG = "package com.vartmp7.stalker.component.RESTOrganizationsRepository";
    private String serverUrl;
    private OkHttpClient httpClient;
    //private int FAIL_RESPONSE_CODE;
    private Gson gson = new Gson();

    private MutableLiveData<List<Organizzazione>> mutableLiveDataOrganizzazioni;
    

    public RESTOrganizationsRepository(OkHttpClient httpClient, String serverUrl) {
        this.httpClient=httpClient;
        this.mutableLiveDataOrganizzazioni = new MutableLiveData<List<Organizzazione>>();
    }


    @Override
    public LiveData<List<Organizzazione>> getOrganizzazioni() {
        //TODO togliere hardcoded-mock e decommentare codice per chiamata alle REST API
        this.mutableLiveDataOrganizzazioni.setValue(Arrays.asList(
                new Organizzazione().setId(1),
                new Organizzazione().setId(2),
                new Organizzazione().setId(3),
                new Organizzazione().setId(4),
                new Organizzazione().setId(5)
        ));

        /*final Request request = new Request.Builder()
                .url(serverUrl)
                .build();
        Call call = httpClient.newCall(request);

        call.enqueue(new Callback() {

            Message msg = new Message();
            Bundle b = new Bundle();

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                b.putInt("CODE", 0);
                b.putString("ErrorMsg", e.getMessage());
                msg.setData(b);
                //error_handler.sendMessage(msg);
                Log.d(TAG, "onFailure: " + e.toString());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    int req_code = Integer.parseInt(Objects.requireNonNull(response.header("req_code")));
                    b.putInt("REQ_CODE", req_code);
                    ResponseOrganizzazione responseOrganizzazione = gson.fromJson(response.body().string(),ResponseOrganizzazione.class);
                    mutableLiveDataOrganizzazioni.setValue(responseOrganizzazione.getOrganizations());
                } catch (NullPointerException e) {
                    b.putInt("CODE", FAIL_RESPONSE_CODE);
                } finally {
                    msg.setData(b);
                   // handler.sendMessage(msg);
                }
            }
        });
        */
        return this.mutableLiveDataOrganizzazioni;

    }


}
