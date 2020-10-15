package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.investigacion.Adaptadores.Negocio;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Negocio> datosNegocios = new ArrayList<>();
    private ImageButton btnImagen;
    public static final int ID_NEGOCIO=1;
    private String Negocio, Propietario, Informacion, Categoria, Ubicacion;
    private LocationManager estadoGPS;
    private Button pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnImagen = findViewById(R.id.btnGPS);
        estadoGPS = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(!estadoGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            btnImagen.setImageResource(android.R.drawable.checkbox_off_background);
        }else{
            btnImagen.setImageResource(android.R.drawable.checkbox_on_background);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ID_NEGOCIO){
            if(RESULT_OK == resultCode){
                this.Negocio = data.getStringExtra("negocio");
                this.Propietario = data.getStringExtra("propietario");
                this.Informacion = data.getStringExtra("informacion");
                this.Categoria = data.getStringExtra("categoria");
                this.Ubicacion = data.getStringExtra("ubicacion");
                Negocio n = new Negocio();
                n.setNombre(Negocio);
                n.setPropietario(Propietario);
                n.setInformacion(Informacion);
                n.setCategoria(Categoria);
                n.setUbicacion(Ubicacion);
                datosNegocios.add(n);
            }
        }
    }

    public void btnRegistro_onClick(View v){
        if(!estadoGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "Activa en GPS para acceder al registro de vendedor", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent frmRegistro = new Intent(this, Registro.class);
            startActivityForResult(frmRegistro,ID_NEGOCIO);
        }
    }
    public void btnListaNegocios_onClick(View v){
        Intent frmListaNegocios = new Intent(this, Lista_Negocios.class);
        startActivity(frmListaNegocios);
    }
    public void CambiarImagen(View v){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);}
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }
    public void onResume(){
        super.onResume();
        if(!estadoGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            btnImagen.setImageResource(android.R.drawable.checkbox_off_background);
        }else{
            btnImagen.setImageResource(android.R.drawable.checkbox_on_background);
        }
    }
}