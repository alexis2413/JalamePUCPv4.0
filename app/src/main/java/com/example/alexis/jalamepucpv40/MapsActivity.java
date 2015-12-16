package com.example.alexis.jalamepucpv40;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private static final LatLng ORIGEN = new LatLng(TripDetail.LatiOrigen, TripDetail.LongOringen);
    private static final LatLng DESTINO = new LatLng(TripDetail.LatiDestino, TripDetail.LongDestino);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                addLines();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        MarkerOptions marker = new MarkerOptions();

        //marker.icon(BitmapDescriptorFactory.fromPath("C:/Users/Alexis/Dropbox/PUCP/2015-2/Programacion en Dispositivos Moviles/EX2/GoogleMapsTest/doge.png"));
        mMap.addMarker(marker.position(ORIGEN).title("PUKE").snippet("Consider yourself located"));
        mMap.addMarker(marker.position(DESTINO).title("SAN ISIDRO").snippet("Consider yourself located"));
    }

    private void addLines() {

        mMap
                .addPolyline((new PolylineOptions())
                        .add(ORIGEN, DESTINO).width(5).color(Color.BLUE)
                        .geodesic(true));
        // move camera to zoom on map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ORIGEN,
                13));
    }
}
