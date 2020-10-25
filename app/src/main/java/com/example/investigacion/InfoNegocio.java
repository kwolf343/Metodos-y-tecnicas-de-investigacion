package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.investigacion.Adaptadores.Negocio;

import java.util.ArrayList;
import java.util.List;

public class InfoNegocio extends AppCompatActivity {

    private List<Negocio> datosDeNegocio = new ArrayList<>();
    private TextView txtNombre, txtPropietario, txtCategoria, txtUbicacion, txtInformacion;
    private Button btnUbicacion;
    private String Nombre, Propietario, Categoria, Ubicacion, Informacion, Lat, Long;
    private int Indice;
    private SharedPreferences vendedores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_negocio);
        ////////////////////////////////////////////////////////////////////////////////////////////
        this.vendedores = getSharedPreferences(Registro.ARCHIVO,MODE_PRIVATE);
        this.datosDeNegocio = SharedPreferenceVendedores.ListaNegocios(vendedores.getString(Registro.KEY,""));
        this.txtNombre = findViewById(R.id.txtNombre_Negocio);
        this.txtPropietario = findViewById(R.id.txtPropietario_Negocio);
        this.txtCategoria = findViewById(R.id.txtCategoria_Negocio);
        this.txtUbicacion = findViewById(R.id.txtUbicacion_Negocio);
        this.txtInformacion = findViewById(R.id.txtInformacion_Negocio);
        this.btnUbicacion = findViewById(R.id.btnUbicacion_Negocio);
        this.Indice = Integer.parseInt(getIntent().getStringExtra("indice"));
        ////////////////////////////////////////////////////////////////////////////////////////////
        this.Nombre = datosDeNegocio.get(Indice).getNombre();
        this.Propietario = datosDeNegocio.get(Indice).getPropietario();
        this.Categoria = datosDeNegocio.get(Indice).getCategoria();
        this.Ubicacion = datosDeNegocio.get(Indice).getUbicacion();
        this.Informacion = datosDeNegocio.get(Indice).getInformacion();
        this.Lat = datosDeNegocio.get(Indice).getCoordenada_Latitud();
        this.Long = datosDeNegocio.get(Indice).getCoordenada_Longitud();
        ////////////////////////////////////////////////////////////////////////////////////////////
        txtNombre.setText(Nombre);
        txtPropietario.setText("Propietario: "+Propietario);
        txtCategoria.setText("Categoría: "+Categoria);
        txtUbicacion.setText("Ubicación: "+Ubicacion);
        txtInformacion.setText("Información: "+Informacion);
    }
    public void verUbicación_onClick(View v){
        Toast.makeText(this, "Latitud: "+Lat+" Longitud: "+Long, Toast.LENGTH_SHORT).show();
    }
    public void verAnuncios_onClick(View v){
        Toast.makeText(this, "Aqui van los anuncios o el menú", Toast.LENGTH_SHORT).show();
    }
}