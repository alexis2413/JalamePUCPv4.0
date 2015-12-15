package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by samoel on 15/12/2015.
 */
public class TripSearchResultActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripsearch_result_activity);

        //Strict Mode
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        List<Trip> resultQuery = TripController.SearchTrips(SearchQuery.origen, SearchQuery.destino);
        TableLayout table = (TableLayout) findViewById(R.id.tblLayout);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        TableRow lblOrigen = new TableRow(this);
        TableRow lblDestino = new TableRow(this);
        TableRow lblFecha = new TableRow(this);
        TableRow lblCapacidad = new TableRow(this);
        TableRow lblDisponibles = new TableRow(this);

        TextView l1 = new TextView(this);
        l1.setText("Origen");
        TextView l2 = new TextView(this);
        l2.setText("Destino");
        TextView l3 = new TextView(this);
        l3.setText("Fecha");
        TextView l4 = new TextView(this);
        l4.setText("Capacidad");
        TextView l5 = new TextView(this);
        l5.setText("Quedan");
        lblOrigen.addView(l1);
        lblDestino.addView(l2);
        lblFecha.addView(l3);
        lblCapacidad.addView(l4);
        lblDisponibles.addView(l5);
        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Columna  origen
        for (int i = 0; i < resultQuery.size(); i++) {
            TextView origen = new TextView(this);
            TextView destino = new TextView(this);
            TextView fecha = new TextView(this);
            TextView capacidad = new TextView(this);
            TextView disponibles = new TextView(this);

            origen.setText(resultQuery.get(i).origen);
            destino.setText(resultQuery.get(i).destino);
            fecha.setText(formatter.format(resultQuery.get(i).fecha));
            int ocupados = resultQuery.get(i).nocupados;
            int npasajeros = resultQuery.get(i).npasajeros;
            int quedan = npasajeros - ocupados;
            capacidad.setText("" + npasajeros);
            disponibles.setText("" + (npasajeros - ocupados));
            lblOrigen.addView(origen);
            lblDestino.addView(destino);
            lblFecha.addView(fecha);
            lblCapacidad.addView(capacidad);
            lblDisponibles.addView(disponibles);
        }
        table.addView(lblOrigen);
        table.addView(lblDestino);
        table.addView(lblFecha);
        table.addView(lblCapacidad);
        table.addView(lblDisponibles);

    }
}

