package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by samoel on 13/12/2015.
 */
public class TripActivity extends Activity {

    @Override
    public void onCreate(Bundle x) {
        super.onCreate(x);
        setContentView(R.layout.registertrips_activity);

        //Spinner Origen
        Spinner spnOrigen = (Spinner) findViewById(R.id.spnOrigen);
        //Spinner Destino
        Spinner spnDestino = (Spinner) findViewById(R.id.spnDestino);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Constantes.Lugares);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOrigen.setAdapter(adapter);
        spnDestino.setAdapter(adapter);

        //boton registrar
        Button btnRegisterTrip = (Button) findViewById(R.id.btnRegisterTrip);
        btnRegisterTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
