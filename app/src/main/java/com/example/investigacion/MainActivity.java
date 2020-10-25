package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnImagen;
    private LocationManager estadoGPS;
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
    public void btnListaNegocios_onClick(View v){
        Intent frmListaNegocios = new Intent(this, Lista_Negocios.class);
        startActivity(frmListaNegocios);
    }
    public void btnLogin1_onClick(View v){
        if(!estadoGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "Activa en GPS para acceder al login", Toast.LENGTH_SHORT).show();
        }else{
            Intent frmLogin = new Intent(this, Login.class);
            frmLogin.putExtra("id","1");
            startActivity(frmLogin);
        }

    }
    public void btnLogin2_onClick(View v){
        if(!estadoGPS.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "Activa en GPS para acceder al login", Toast.LENGTH_SHORT).show();
        }else{
        Intent frmLogin = new Intent(this, Login.class);
        frmLogin.putExtra("id","2");
        startActivity(frmLogin);
        }
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