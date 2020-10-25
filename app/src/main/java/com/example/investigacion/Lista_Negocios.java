package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
    private SharedPreferences vendedores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__negocios);
        this.vendedores = getSharedPreferences(Registro.ARCHIVO,MODE_PRIVATE);
        datosDeNegocio = SharedPreferenceVendedores.ListaNegocios(vendedores.getString(Registro.KEY,""));
        this.listNegocios = findViewById(R.id.listNegocios);
        this.manager = new LinearLayoutManager(this);
        this.adaptador = new AdaptadorNegocio(datosDeNegocio);
        //Configuramos el RecyclerView
        this.listNegocios.setHasFixedSize(true);
        this.listNegocios.setLayoutManager(this.manager);
        this.listNegocios.setAdapter(this.adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(Lista_Negocios.this, InfoNegocio.class);
                info.putExtra("indice",Integer.toString(listNegocios.getChildAdapterPosition(view)));
                startActivity(info);
            }
        });
    }

}