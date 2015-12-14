package com.example.alexis.jalamepucpv40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by samoel on 13/12/2015.
 */
public class LogInController {

    public static boolean LogIn(String usuario, String password) {
        String url = Constantes.Url + "login/" + usuario + "/" + password;
        HttpURLConnection con;
        InputStream is;
        boolean login = false;
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
            String respuesta = sb.toString();
            if (Constantes.LogInConfirmation.compareTo(respuesta) == 0) {
                login = true;
                return true;
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return login;

    }

    public static String RegisterUser(String nombre, String usuario, String pass, String codPucp, String correo, String distrito,
                                      String telefono) {
        String url = Constantes.Url + "usuarios/" + nombre + "/" + usuario + "/" + pass + "/" + codPucp + "/" + correo + "/" + distrito
                + "/" + telefono;
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
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean VerificaRepetido(String username) {
        String url = Constantes.Url + "verifica_repetido/" + username;
        HttpURLConnection con;
        InputStream is;
        boolean repetido = true;
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
            String respuesta = sb.toString();
            if (Constantes.Repetido.compareTo(respuesta) == 0) {
                return true;
            } else
                return repetido;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repetido;
    }
}
