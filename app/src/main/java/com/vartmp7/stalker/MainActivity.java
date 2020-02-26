package com.vartmp7.stalker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vartmp7.stalker.GsonBeans.Coordinata;
import com.vartmp7.stalker.GsonBeans.Luogo;
import com.vartmp7.stalker.GsonBeans.Organizzazione;
import com.vartmp7.stalker.GsonBeans.ResponseLuogo;
import com.vartmp7.stalker.GsonBeans.ResponseOrganizzazione;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.vartmp7.stalker.Tools.getUnsafeOkHttpClient;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private final String TAG = "MainActivity";
    private Spinner sScegliOrganizzazione;
    private TextView tvStatus;

    private ArrayList<Organizzazione> organizzazioni;
    private ArrayList<Luogo> luoghi;
    private TextView tvCurrentStatus;
    private int[] viewToshowOnChoice = {R.id.tvTestoElenco, R.id.tvElencoLuoghi, R.id.btnStartTracking};
    private int[] viewToShowOnTracking = {R.id.tvStatus, R.id.tvCurrentStatus};
    private OkHttpClient client;
    private int FAIL_RESPONSE_CODE = 0;
    private int SUCCESSFUL_RESPONSE_CODE = 1;
    public final static String SERVER = "https://10.0.2.2:5000/";
    private LocationManager locationManager;

    private TextView tvLuoghi;


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
        client = getUnsafeOkHttpClient();
        sScegliOrganizzazione = findViewById(R.id.s_scegliOrganizzazione);
        sScegliOrganizzazione.setSelected(false);

        tvStatus = findViewById(R.id.tvStatus);

        findViewById(R.id.btnStartTracking).setOnClickListener(this);
        findViewById(R.id.btnRefresh).setOnClickListener(this);
//        loadOrganizazzione();

        tvCurrentStatus = findViewById(R.id.tvCurrentStatus);
        tvLuoghi = findViewById(R.id.tvElencoLuoghi);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleziona un'organizzazione..."});

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sScegliOrganizzazione.setAdapter(adapter);
        sScegliOrganizzazione.setOnItemSelectedListener(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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
            case 0:
                if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startTracking();
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
                b.putString("ErrorMsg", e.getMessage());
                msg.setData(b);
                error_handler.sendMessage(msg);
//                Log.d(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                try {
                    int req_code = Integer.parseInt(Objects.requireNonNull(response.header("req_code")));
                    b.putInt("REQ_CODE", req_code);
                    String str = response.body().string();
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


    @SuppressLint("HandlerLeak")
    private Handler error_handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            ResponseOrganizzazione r = new ResponseOrganizzazione();
            r.setOrganizations(new ArrayList<Organizzazione>());
            loadOrganizazzione(r);
            Toast.makeText(MainActivity.this, msg.getData().getString("ErrorMsg"), Toast.LENGTH_SHORT).show();

        }
    };
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
                    Toast.makeText(MainActivity.this, "Something failed!", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void updateLuoghi(ResponseLuogo l) {
//        tvLuoghi.setText("Ciaoasd");
        luoghi= l.getPlaces();
        tvLuoghi.setText(l.getDataForSpinner());
    }

    private void loadOrganizazzione(ResponseOrganizzazione orgs) {

        String[] mList = orgs.getDataForSpinner();
        organizzazioni = orgs.getOrganizations();
        if (organizzazioni.size() == 0) {
            mList = new String[]{"Non ci sono organizzazioni!"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mList);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sScegliOrganizzazione.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(MainActivity.this, "selezionato " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

        if (position == 0) {
            hideView(viewToshowOnChoice);
            hideView(viewToShowOnTracking);
            return;
        }

        String req = String.format("organizations/%s/places", organizzazioni.get(position - 1).getId());
//        Log.d(TAG, "onItemSelected: REQ" + SERVER + req);
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


    private void startTracking(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,-1,
                    (float) luoghi.get(0).getRadius(),
                    new Tracker(MainActivity.this, new StalkerCallBack() {
                @Override
                public boolean onLocationsChanged(Location l) {
                    Coordinata c = new Coordinata( l.getLatitude(), l.getLongitude());

                    for (Luogo luogo : luoghi) {
                       if (luogo.isInPlace(c)){
                           tvCurrentStatus.setText("you are in "+luoghi.get(0).getName());
                           return true;
                       }else{
                           tvCurrentStatus.setText("You left "+ luoghi.get(0).getName());

                           return false;
                       }

                    }
                    return false;
                }
            }));
        }else
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartTracking:
                Toast.makeText(MainActivity.this, sScegliOrganizzazione.getSelectedItem() + " ti sta tracciando!", Toast.LENGTH_SHORT).show();
                showView(viewToShowOnTracking);

                tvCurrentStatus.setText(sScegliOrganizzazione.getSelectedItem()+" ti sta tracciando!");
                startTracking();

                break;
            case R.id.btnRefresh:
                get(SERVER + "organizations");
                break;

        }
    }
}
