package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.investigacion.Adaptadores.Negocio;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends AppCompatActivity {
    private int numerador;
    private SharedPreferences vendedores;
    private List<Negocio> datosDeNegocio = new ArrayList<>();
    private TextView Nombre,Propietario,Categoria,Ubicacion,Informacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);
        this.numerador = Integer.parseInt(getIntent().getStringExtra("numerador"));
        this.vendedores = getSharedPreferences(com.example.investigacion.Registro.ARCHIVO,MODE_PRIVATE);
        datosDeNegocio = SharedPreferenceVendedores.ListaNegocios(vendedores.getString(Registro.KEY,""));
        this.Nombre = findViewById(R.id.txtNombreDelNegocio);
        this.Propietario = findViewById(R.id.txtPropietarioDelNegocio);
        this.Categoria = findViewById(R.id.txtCategoriaDelNegocio);
        this.Ubicacion = findViewById(R.id.txtUbicacionDelNegocio);
        this.Informacion = findViewById(R.id.txtInformacionDelNegocio);

        Nombre.setText(datosDeNegocio.get(numerador).getNombre());
        Propietario.setText(datosDeNegocio.get(numerador).getPropietario());
        Categoria.setText(datosDeNegocio.get(numerador).getCategoria());
        Ubicacion.setText(datosDeNegocio.get(numerador).getUbicacion());
        Informacion.setText(datosDeNegocio.get(numerador).getInformacion());
    }

}