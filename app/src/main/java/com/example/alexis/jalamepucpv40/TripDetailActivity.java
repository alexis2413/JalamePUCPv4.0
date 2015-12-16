package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samoel on 16/12/2015.
 */
public class TripDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripdetail_activity);

        List<Integer> pasajeros = TripController.TripPassangers(TripDetail.detalleViaje);
        if (pasajeros == null || pasajeros.size() == 0) {
            TextView txtMensaje = (TextView) findViewById(R.id.txtVacio);
            txtMensaje.setText("Todavía no hay pasajeros");
        } else {
            List<User> pasajerosU = new ArrayList<User>();
            for (int i = 0; i < pasajeros.size(); i++) {
                User user = LogInController.ObtieneUsuarioId(pasajeros.get(i));
                pasajerosU.add(user);
            }

            TableLayout table = (TableLayout) findViewById(R.id.lyPasajeros);
            table.setBackgroundColor(Color.WHITE);
            table.setStretchAllColumns(true);
            table.setShrinkAllColumns(true);

            TableRow rowTitle = new TableRow(this);
            rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

            TableRow header = new TableRow(this);
            TextView l0 = new TextView(this);
            l0.setText("N°");
            TextView l1 = new TextView(this);
            l1.setText("Pasajero");
            header.addView(l0);
            header.addView(l1);
            table.addView(header);

            for (int i = 0; i < pasajerosU.size(); i++) {
                User user = pasajerosU.get(i);
                TextView numero = new TextView(this);
                TextView nombre = new TextView(this);
                numero.setText("" + (i + 1));
                nombre.setText(user.nombre);
                TableRow fila = new TableRow(this);
                fila.addView(numero);
                fila.addView(nombre);
                table.addView(fila);
            }
        }
        final Context context = this;
        Button btnVerRutaView = (Button) findViewById(R.id.btnVerRutaView);
        btnVerRutaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
