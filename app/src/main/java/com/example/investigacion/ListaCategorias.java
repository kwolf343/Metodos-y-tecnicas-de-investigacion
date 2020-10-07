package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaCategorias extends AppCompatActivity {

    private ListView listview;
    private ArrayList<String> names;
    public static final String TAG_MENSAJE = "Msj";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        listview = (ListView) findViewById(R.id.ListadoCategorias);

        names = new ArrayList<String>();
        names.add("Pupusas");
        names.add("Comida mexicana");
        names.add("Comida china");
        names.add("Pizzeria");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0 && i<names.size()){
                    Intent mensajePadre = new Intent();
                    mensajePadre.putExtra(TAG_MENSAJE, names.get(i));
                    setResult(RESULT_OK, mensajePadre);
                    finish();
                }

            }
        });
    }
}