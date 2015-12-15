package com.example.alexis.jalamepucpv40;

import java.util.Date;

/**
 * Created by samoel on 15/12/2015.
 */
public class Trip {
    public int owner;
    public String origen;
    public String destino;
    public Date fecha;
    public int npasajeros;
    public int nocupados;

    public Trip(int owner, String origen, String destino, Date fecha, int npasj, int nopc) {
        this.owner = owner;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.npasajeros = npasj;
        this.nocupados = nopc;
    }
}
