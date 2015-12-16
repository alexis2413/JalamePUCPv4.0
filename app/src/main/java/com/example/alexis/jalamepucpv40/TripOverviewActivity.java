package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by samoel on 15/12/2015.
 */
public class TripOverviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tripoverview_activity);
        final Context context = this;
        List<Trip> resultQuery = TripController.MyTrips();
        TableLayout table = (TableLayout) findViewById(R.id.lyTablaMV);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        TableRow header = new TableRow(this);
        TextView l0 = new TextView(this);
        l0.setText("N°");
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
        header.addView(l0);
        header.addView(l1);
        header.addView(l2);
        header.addView(l3);
        header.addView(l4);
        header.addView(l5);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Columna  origen
        table.addView(header);
        //seleccioando
        final TextView txtSeleccionado = (TextView) findViewById(R.id.txtSeleccionado);
        for (int i = 0; i < resultQuery.size(); i++) {
            TextView numero = new TextView(this);
            TextView origen = new TextView(this);
            TextView destino = new TextView(this);
            TextView fecha = new TextView(this);
            TextView capacidad = new TextView(this);
            TextView disponibles = new TextView(this);

            numero.setText("" + resultQuery.get(i).id);
            origen.setText(resultQuery.get(i).origen);
            destino.setText(resultQuery.get(i).destino);
            fecha.setText(formatter.format(resultQuery.get(i).fecha));
            int ocupados = resultQuery.get(i).nocupados;
            int npasajeros = resultQuery.get(i).npasajeros;
            int quedan = npasajeros - ocupados;
            capacidad.setText("" + npasajeros);
            disponibles.setText("" + quedan);
            final TableRow fila = new TableRow(this);
            fila.addView(numero);
            fila.addView(origen);
            fila.addView(destino);
            fila.addView(fecha);
            fila.addView(capacidad);
            fila.addView(disponibles);
            table.addView(fila);
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView id = (TextView) fila.getChildAt(0);
                    int idViaje = Integer.parseInt(id.getText().toString());
                    TextView sOrigen = (TextView) fila.getChildAt(1);
                    TextView sDestino = (TextView) fila.getChildAt(2);
                    String ssOrigen = sOrigen.getText().toString();
                    String ssDestino = sDestino.getText().toString();
                    int indOrigen = Constantes.Lugares.indexOf(ssOrigen);
                    int indDestino = Constantes.Lugares.indexOf(ssDestino);
                    TripDetail.detalleViaje = idViaje;
                    TripDetail.LatiOrigen = Constantes.Latitud.get(indOrigen);
                    TripDetail.LongOrigen = Constantes.Longitud.get(indOrigen);
                    TripDetail.LatiDestino = Constantes.Latitud.get(indDestino);
                    TripDetail.LongDestino = Constantes.Longitud.get(indDestino);
                    Intent intent = new Intent(context, TripDetailActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
