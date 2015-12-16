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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samoel on 13/12/2015.
 */
public class LogInController {

    public static int usuario;

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

    public static int ObtieneUsuario(String username) {
        String url = Constantes.Url + "usuarios/busca/" + username;
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
            if (sb.toString().compareTo(Constantes.UsuarioGet) != 0) {
                JSONArray jsonArray = new JSONArray(sb.toString());
                JSONObject json = jsonArray.getJSONObject(0);
                int id = Integer.parseInt(json.getString("id"));
                return id;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static User ObtieneUsuarioId(int username) {
        String url = Constantes.Url + "usuarios/buscaId/" + username;
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
            if (sb.toString().compareTo("No hay") == 0) return null;
            JSONArray jsonArray = new JSONArray(sb.toString());
            JSONObject json = jsonArray.getJSONObject(0);
            int id = json.getInt("id");
            String nombre = json.getString("nombre");
            User resultado = new User(id, nombre, "", "", "", "", "");
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

}
