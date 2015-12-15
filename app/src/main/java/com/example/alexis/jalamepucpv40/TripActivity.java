package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by samoel on 13/12/2015.
 */
public class TripActivity extends Activity {

    @Override
    public void onCreate(Bundle x) {
        super.onCreate(x);
        setContentView(R.layout.registertrips_activity);

        //contexto
        final Context context = this;
        //Spinner Origen
        final Spinner spnOrigen = (Spinner) findViewById(R.id.spnOrigen);
        //Spinner Destino
        final Spinner spnDestino = (Spinner) findViewById(R.id.spnDestino);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Constantes.Lugares);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOrigen.setAdapter(adapter);
        spnDestino.setAdapter(adapter);

        //Datos del viaje
        final EditText etFecha = (EditText) findViewById(R.id.etFecha);
        final EditText npasj = (EditText) findViewById(R.id.etNPasajeros);
        //boton registrar
        Button btnRegisterTrip = (Button) findViewById(R.id.btnRegisterTrip);
        btnRegisterTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origen = spnOrigen.getSelectedItem().toString();
                origen = origen.replace(" ","%20");
                String destino = spnDestino.getSelectedItem().toString();
                destino = destino.replace(" ","%20");
                String fecha = etFecha.getText().toString();
                fecha = fecha.replace("/", "-");
                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date fechaNueva = formater.parse(fecha);
                    int nPasajeros = Integer.parseInt(npasj.getText().toString());
                    TripController.RegisterTrip(origen, destino, fechaNueva, nPasajeros);
                    Toast.makeText(getApplicationContext(), "Viaje Registrado Correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MenuActivity.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "Ocurrio un error inesperado", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
