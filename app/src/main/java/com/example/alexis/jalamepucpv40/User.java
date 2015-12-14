package com.example.alexis.jalamepucpv40;

/**
 * Created by samoel on 13/12/2015.
 */
public class User {
    public int id;
    public String nombre;
    public String usuario;
    public String password;
    public String codPucp;
    public String correo;
    public String distrito;
    public String telefono;

    public User(int id, String nombre, String usuario, String password, String codPucp, String correo, String distrito, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.codPucp = codPucp;
        this.correo = correo;
        this.distrito = distrito;
        this.telefono = telefono;
    }
}
