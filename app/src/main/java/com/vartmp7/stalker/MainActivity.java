package com.vartmp7.stalker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vartmp7.stalker.GsonBeans.Luogo;
import com.vartmp7.stalker.GsonBeans.Organizzazione;
import com.vartmp7.stalker.GsonBeans.ResponseLuogo;
import com.vartmp7.stalker.GsonBeans.ResponseOrganizzazione;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private final String TAG = "MainActivity";
    private Spinner sScegliOrganizzazione;
    private TextView tvStatus;

    private ArrayList<Organizzazione> organizzazioni;
    private TextView tvCurrentStatus;
    private int[] viewToshowOnChoice = {R.id.tvTestoElenco, R.id.tvElencoLuoghi, R.id.btnStartTracking};
    private int[] viewToShowOnTracking = {R.id.tvStatus, R.id.tvCurrentStatus};
    private OkHttpClient client;
    private int FAIL_RESPONSE_CODE = 0;
    private int SUCCESSFUL_RESPONSE_CODE = 1;
    public final static String SERVER = "https://10.0.2.2:5000/";


    private TextView tvLuoghi;

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)
            get(SERVER + "organizations");
        else
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        String s="{\n" +
//                "  \"places\": [\n" +
//                "    {\n" +
//                "      \"approved\": false,\n" +
//                "      \"coordinates\": [\n" +
//                "        {\n" +
//                "          \"latitude\": 1.1,\n" +
//                "          \"longitude\": 1.2\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"latitude\": 2.1,\n" +
//                "          \"longitude\": 2.2\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"latitude\": 3.1,\n" +
//                "          \"longitude\": 3.2\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"latitude\": 3.1,\n" +
//                "          \"longitude\": 3.2\n" +
//                "        }\n" +
//                "      ],\n" +
//                "      \"id\": 1,\n" +
//                "      \"name\": \"nuovo luogo\",\n" +
//                "      \"num_max_people\": 100\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//
//        Gson gson = new Gson();
//        ResponseLuogo l = gson.fromJson(s, ResponseLuogo.class);
//
//        Log.d(TAG, "onCreate: "+l.toString());
//        OkHttpClient.Builder b = new OkHttpClient.Builder();

//        client = new OkHttpClient();
        client = getUnsafeOkHttpClient();
        sScegliOrganizzazione = findViewById(R.id.s_scegliOrganizzazione);
        sScegliOrganizzazione.setSelected(false);

        tvStatus = findViewById(R.id.tvStatus);

        findViewById(R.id.btnStartTracking).setOnClickListener(this);
//        loadOrganizazzione();

        tvCurrentStatus = findViewById(R.id.tvCurrentStatus);
        tvLuoghi = findViewById(R.id.tvElencoLuoghi);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    get(SERVER + "organizations");
                } else {
                    Toast.makeText(this, "I need permissions ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    void get(String url) {

        final Request request = new Request.Builder()
                .url(url)
                .build();


        Call call = client.newCall(request);


        call.enqueue(new Callback() {
            Message msg = new Message();
            Bundle b = new Bundle();

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                b.putInt("CODE", 0);
                msg.setData(b);
                handler.sendMessage(msg);
                Log.d(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                try {


                    int req_code = Integer.parseInt(Objects.requireNonNull(response.header("req_code")));

                    Log.d(TAG, "onResponse: REQ_CODE: " + req_code);

                    b.putInt("REQ_CODE", req_code);


                    String str = response.body().string();

                    Log.d(TAG, "onResponse: " + str);

                    b.putString("MSG", str);
                } catch (NullPointerException e) {
                    b.putInt("CODE", FAIL_RESPONSE_CODE);
                } finally {

                    msg.setData(b);
                    handler.sendMessage(msg);
                }
            }
        });

    }

    private final int REQ_ORG = 0;
    private final int REQ_LUOGHI = 5;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        private Gson gson = new Gson();

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            int code = msg.getData().getInt("REQ_CODE");
            String s = msg.getData().getString("MSG");
            switch (code) {
                case REQ_ORG:
                    loadOrganizazzione(gson.fromJson(s, ResponseOrganizzazione.class));
                    break;
                case REQ_LUOGHI:
                    updateLuoghi(gson.fromJson(s, ResponseLuogo.class));
                    // aggiornare i luoghi
                    break;
                default:
                        Toast.makeText(MainActivity.this,"Something failed!", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void updateLuoghi(ResponseLuogo l) {
//        tvLuoghi.setText("Ciaoasd");
        tvLuoghi.setText(l.getDataForSpinner());
    }

    private void loadOrganizazzione(ResponseOrganizzazione orgs) {

        String[] mList = orgs.getDataForSpinner();
        organizzazioni = orgs.getOrganizations();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mList);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sScegliOrganizzazione.setAdapter(adapter);
        sScegliOrganizzazione.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(MainActivity.this, "selezionato " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

        if (position == 0) {
            hideView(viewToshowOnChoice);
            hideView(viewToShowOnTracking);
            return;
        }

//        Organizzazione org = (Organizzazione) parent.getSele;

        //todo richiedere i luoghi dell'organizzazione
        String req = String.format("organizations/%s/places", organizzazioni.get(position - 1).getId());
        Log.d(TAG, "onItemSelected: REQ" + SERVER + req);
        get(SERVER + req);
        showView(viewToshowOnChoice);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void showView(int[] views) {
        for (int i : views) {
            findViewById(i).setVisibility(View.VISIBLE);
        }
    }

    private void hideView(int[] views) {
        for (int i : views) {
            findViewById(i).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartTracking:
                Toast.makeText(MainActivity.this, sScegliOrganizzazione.getSelectedItem() + " ti sta tracciando!", Toast.LENGTH_SHORT).show();
                tvCurrentStatus.setText(R.string.app_name);
                showView(viewToShowOnTracking);
                break;

        }
    }
}
