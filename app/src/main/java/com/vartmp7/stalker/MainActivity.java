package com.vartmp7.stalker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vartmp7.stalker.GsonBeans.ResponseOrganizzazione;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner sScegliOrganizzazione;
    private TextView tvStatus;
    private TextView tvCurrentStatus;
    private int[] viewToshowOnChoice = {R.id.tvTestoElenco, R.id.tvElencoLuoghi, R.id.btnStartTracking};
    private int[] viewToShowOnTracking = {R.id.tvStatus, R.id.tvCurrentStatus};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sScegliOrganizzazione = findViewById(R.id.s_scegliOrganizzazione);
        sScegliOrganizzazione.setSelected(false);

        RotateAnimation ani = new RotateAnimation(0.0f, 0.50f, 0, 0, 40, 0);
        ani.setDuration(2000);

        sScegliOrganizzazione.setAnimation(ani);
        tvStatus = findViewById(R.id.tvStatus);

        findViewById(R.id.btnStartTracking).setOnClickListener(this);
        loadOrganizazzione();



    }

    private void loadOrganizazzione() {

        // facciamo finta che la stringa l'abbia ottenuta con una richeista http
        String s = "{\n" +
                "    \"organizations\": [\n" +
                "        {\n" +
                "            \"address\": \"Via salcazzo, 9\",\n" +
                "            \"city\": \"Città\",\n" +
                "            \"email\": \"org@organizzazione.it\",\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Nuova organizazione\",\n" +
                "            \"nation\": \"Italy\",\n" +
                "            \"phone_number\": \"+391234567890\",\n" +
                "            \"postal_code\": \"35010\",\n" +
                "            \"region\": \"Regione\",\n" +
                "            \"type\": \"both\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        Gson gson = new Gson();

        ResponseOrganizzazione orgs = gson.fromJson(s, ResponseOrganizzazione.class);
        String[] mList = orgs.getDataForSpinner();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mList);
        sScegliOrganizzazione.setAdapter(adapter);
        sScegliOrganizzazione.setOnItemSelectedListener(this);
    }

//   onItemClick delle organizzazioni

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, "selezionato " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();

        if (position == 0) {
            hideView(viewToshowOnChoice);
            hideView(viewToShowOnTracking);
            return;
        }

        //todo richiedere i luoghi dell'organizzazione


        String s = "elenco delle organizzazioni";

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
                Toast.makeText(MainActivity.this, "Starting tracking!", Toast.LENGTH_SHORT).show();
                showView(viewToShowOnTracking);
                break;

        }
    }
}
