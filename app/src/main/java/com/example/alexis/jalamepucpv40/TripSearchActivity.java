package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by samoel on 15/12/2015.
 */
public class TripSearchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripsearch_activity);

        //boton que busca el query
        Button btnQuery = (Button) findViewById(R.id.btnSearchTrip);

        //spinners
        final Spinner spnQOrigen = (Spinner) findViewById(R.id.spnQueryOrigen);
        final Spinner spnQDestino = (Spinner) findViewById(R.id.spnQueryDestino);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Constantes.Lugares);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnQOrigen.setAdapter(adapter);
        spnQDestino.setAdapter(adapter);

        final Context context = this;
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = spnQOrigen.getSelectedItem().toString();
                origen = origen.replace(" ", "%20");
                String destino = spnQDestino.getSelectedItem().toString();
                destino = destino.replace(" ", "%20");
                SearchQuery.destino = destino;
                SearchQuery.origen = origen;
                Intent intent = new Intent(context, TripSearchResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
