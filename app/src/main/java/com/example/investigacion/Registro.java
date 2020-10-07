package com.example.investigacion;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Registro extends AppCompatActivity {

    //este es un comentario


    private EditText txtNegocio, txtPropietario, txtInfo;
    private String Categoria, Ubicacion;
    private LocationManager ubicacion;
    public static final int ID_CATEGORIAS=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.txtNegocio = findViewById(R.id.txtNombrePropietario);
        this.txtPropietario = findViewById(R.id.txtNombreNegocio);
        this.txtInfo = findViewById(R.id.txtInfo);
        this.Categoria="";
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ID_CATEGORIAS){
            if(RESULT_OK == resultCode){
                String msjActivityHija = data.getStringExtra(ListaCategorias.TAG_MENSAJE);
                this.Categoria=msjActivityHija;
            }
        }
    }
    public void listarCategorias_onClick(View v){
        Intent frmListado = new Intent(this,ListaCategorias.class);
        startActivityForResult(frmListado,ID_CATEGORIAS);
    }

    //Localizacion obtiene la latitud y longitud de mi dispositivo
    /*public void Localizacion(View v) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(ubicacion !=null){
            String latitud = String.valueOf(loc.getLatitude());
            String longitud = String.valueOf(loc.getLongitude());
            Toast.makeText(this, longitud, Toast.LENGTH_SHORT).show();
        }
    }*/
    public String obtenerUbicacion(){
        String posUbicacion="";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    String calle = DirCalle.getAddressLine(0);
                    posUbicacion = calle;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return posUbicacion;
    }
    public void Ubicacion(View v) {
        Toast.makeText(this, Ubicacion, Toast.LENGTH_SHORT).show();
    }

    public void Aceptar_onClick(View v){
        this.Ubicacion=obtenerUbicacion();
        String NombreNegocio = txtNegocio.getText().toString();
        String NombrePropietario = txtPropietario.getText().toString();
        String Informacion = txtInfo.getText().toString();

        if(NombreNegocio.equals("") || NombrePropietario.equals("") || Informacion.equals("") || Categoria.equals("")){
            Toast.makeText(this, "Debe completar los datos antes de enviar", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent mensajePadre = new Intent();
            mensajePadre.putExtra("negocio", NombreNegocio);
            mensajePadre.putExtra("propietario", NombrePropietario);
            mensajePadre.putExtra("informacion", Informacion);
            mensajePadre.putExtra("categoria",Categoria);
            mensajePadre.putExtra("ubicacion",Ubicacion);
            setResult(RESULT_OK, mensajePadre);
            finish();
        }
    }
}