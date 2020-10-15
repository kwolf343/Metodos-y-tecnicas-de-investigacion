package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.investigacion.Adaptadores.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroUsuario extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        final EditText nombreT = (EditText) findViewById(R.id.Rnombre);
        final EditText NombreNegocioT = (EditText) findViewById(R.id.RnombreNegocio);
        final EditText CorreoT = (EditText) findViewById(R.id.RnombreNegocio);
        final EditText CredencialT = (EditText) findViewById(R.id.RCredencial);
        final EditText DescripcionT = (EditText) findViewById(R.id.Rdescripcion);
        Button Registrar = (Button) findViewById(R.id.btnRegistro);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = nombreT.getText().toString();
                String NombreNregocio = NombreNegocioT.getText().toString();
                String Correo = CorreoT.getText().toString();
                String Credencial = CredencialT.getText().toString();
                String Descripcion = DescripcionT.getText().toString();


                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");

                            if(ok == true){
                                Toast.makeText(RegistroUsuario.this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistroUsuario.this, Login.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(RegistroUsuario.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                            }

                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };

                RegistroRequest r = new RegistroRequest(Nombre, NombreNregocio, Correo, Credencial, Descripcion, respuesta);
                RequestQueue cola = Volley.newRequestQueue(RegistroUsuario.this);
                cola.add(r);
            }
        });

    }
}