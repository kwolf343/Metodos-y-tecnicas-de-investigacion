package com.example.investigacion;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Registro extends AppCompatActivity {

    private EditText txtNegocio, txtPropietario, txtInfo, txtClave;
    private String Categoria, Ubicacion, Latitud, Longitud;
    private LocationManager ubicacion;
    public static final int ID_CATEGORIAS=1;
    private SharedPreferences vendedores;
    public static final String ARCHIVO = "archivo";
    public static final String KEY = "llave";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.txtNegocio = findViewById(R.id.txtNombreNegocio);
        this.txtPropietario = findViewById(R.id.txtNombrePropietario);
        this.txtInfo = findViewById(R.id.txtInfo);
        this.txtClave = findViewById(R.id.txtClave);
        this.Categoria="";
        this.vendedores = getSharedPreferences(ARCHIVO,MODE_PRIVATE);
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
                    this.Latitud = String.valueOf(loc.getLatitude());
                    this.Longitud = String.valueOf(loc.getLongitude());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return posUbicacion;
    }

    public void Aceptar_onClick(View v){
        this.Ubicacion=obtenerUbicacion();
        String NombreNegocio = txtNegocio.getText().toString();
        String NombrePropietario = txtPropietario.getText().toString();
        String Informacion = txtInfo.getText().toString();
        String Clave = txtClave.getText().toString();
        if(NombreNegocio.equals("") || NombrePropietario.equals("") || Informacion.equals("") || Categoria.equals("")){
            Toast.makeText(this, "Debe completar los datos antes de enviar", Toast.LENGTH_SHORT).show();
        }
        else {
            if(this.vendedores!=null){
                String lista = vendedores.getString(KEY,"");
                if(SharedPreferenceVendedores.ListaNegocios(lista).size()==0){
                    SharedPreferences.Editor editor = this.vendedores.edit();
                    editor.putString(KEY, NombreNegocio+"="+NombrePropietario+"="+Informacion+"="+Categoria+"="+Ubicacion+"="+Latitud+"="+Longitud+"="+Clave);
                    if(editor.commit()){
                        Log.d("TAG","Informacion guardada");
                    }else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    SharedPreferences.Editor editor = this.vendedores.edit();
                    editor.putString(KEY, vendedores.getString(KEY,"")+";"+NombreNegocio+"="+NombrePropietario+"="+Informacion+"="+Categoria+"="+Ubicacion+"="+Latitud+"="+Longitud+"="+Clave);
                    editor.putString(KEY,"");
                    if(editor.commit()){
                        Log.d("TAG","Informacion guardada");
                    }else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            finish();
        }

    }
}