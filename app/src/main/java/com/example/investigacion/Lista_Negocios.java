package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.investigacion.Adaptadores.AdaptadorNegocio;
import com.example.investigacion.Adaptadores.Negocio;

import java.util.ArrayList;
import java.util.List;

public class Lista_Negocios extends AppCompatActivity {

    private List<Negocio> datosDeNegocio = new ArrayList<>();
    private RecyclerView listNegocios;
    private AdaptadorNegocio adaptador;
    private LinearLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__negocios);

        datosDeNegocio = MainActivity.datosNegocios;
        this.listNegocios = findViewById(R.id.listNegocios);
        this.manager = new LinearLayoutManager(this);
        this.adaptador = new AdaptadorNegocio(datosDeNegocio);

        //Configuramos el RecyclerView
        this.listNegocios.setHasFixedSize(true);
        this.listNegocios.setLayoutManager(this.manager);
        this.listNegocios.setAdapter(this.adaptador);
    }
    public void saludar(View v){
        TextView lblN = findViewById(R.id.lblNombre);
        String name = lblN.getText().toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}