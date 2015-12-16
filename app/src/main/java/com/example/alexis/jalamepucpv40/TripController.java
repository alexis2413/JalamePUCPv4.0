package com.example.alexis.jalamepucpv40;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by samoel on 14/12/2015.
 */
public class TripController {

    public static List<Trip> MyTrips() {
        int idUsuario = LogInController.usuario;
        String url = Constantes.Url + "viajes/mis_viajes/" + idUsuario;
        HttpURLConnection con;
        InputStream is;
        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            StringBuffer sb = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            is.close();
            con.disconnect();

            JSONArray jsonArray = new JSONArray(sb.toString());
            List<Trip> resultado = new ArrayList<Trip>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                int id = json.getInt("id");
                int owner = json.getInt("idUsuario");
                String _origen = json.getString("origen");
                String _destino = json.getString("destino");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String fechaS = json.getString("fecha");
                try {
                    Date fecha = formatter.parse(fechaS);
                    int npasj = json.getInt("numAsientos");
                    int nocp = json.getInt("ocupados");
                    Trip trip = new Trip(id, owner, _origen, _destino, fecha, npasj, nocp);
                    resultado.add(trip);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return resultado;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void AddPassenger(int idViaje) {
        int usuario = LogInController.usuario;
        String url = Constantes.Url + "viajes/ingresa_pasajero/" + idViaje + "/" + usuario;
        HttpURLConnection con;
        InputStream is;
        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            StringBuffer sb = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            is.close();
            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Trip> SearchTrips(String origen, String destino) {
        String url = Constantes.Url + "viajes/busca/" + origen + "/" + destino;
        HttpURLConnection con;
        InputStream is;
        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            StringBuffer sb = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            is.close();
            con.disconnect();

            JSONArray jsonArray = new JSONArray(sb.toString());
            List<Trip> resultado = new ArrayList<Trip>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                int id = json.getInt("id");
                int owner = json.getInt("idUsuario");
                String _origen = json.getString("origen");
                String _destino = json.getString("destino");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String fechaS = json.getString("fecha");
                try {
                    Date fecha = formatter.parse(fechaS);
                    int npasj = json.getInt("numAsientos");
                    int nocp = json.getInt("ocupados");
                    Trip trip = new Trip(id, owner, _origen, _destino, fecha, npasj, nocp);
                    resultado.add(trip);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return resultado;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void RegisterTrip(String origen, String destino, Date fecha, int numPasajeros) {
        int usuario = LogInController.usuario;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(fecha);
        String url = Constantes.Url + "viajes/" + usuario + "/" + origen + "/" + destino + "/" + fechaString + "/" + numPasajeros;
        HttpURLConnection con;
        InputStream is;
        try {
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            StringBuffer sb = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            is.close();
            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
