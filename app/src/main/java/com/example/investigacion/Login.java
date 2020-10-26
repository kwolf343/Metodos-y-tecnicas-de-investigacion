package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.investigacion.Adaptadores.LoginRequest;
import com.example.investigacion.Adaptadores.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private int identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button Login = (Button) findViewById(R.id.btnLoginIngresar);
        final TextView Registro = (TextView) findViewById(R.id.txvRegistro);
        final EditText UsuarioL = (EditText) findViewById(R.id.LoginUser);
        final EditText CredencialL = (EditText) findViewById(R.id.LoginPass);
        this.identificador = Integer.parseInt(getIntent().getStringExtra("id"));


        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(identificador == 1){
                    Intent i = new Intent(Login.this, RegistroUsuario.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(Login.this, Registro.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Correo = UsuarioL.getText().toString();
                String Credencial = CredencialL.getText().toString();

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok == true){
                                Toast.makeText(Login.this, "Datos Correctos", Toast.LENGTH_SHORT).show();

                               if(identificador == 1){
                                   Intent i = new Intent(Login.this, Lista_Negocios.class);
                                   startActivity(i);
                                   finish();
                               }else{
                                   Intent i = new Intent(Login.this, ofertas.class);
                                   startActivity(i);
                                   finish();
                               }

                            }else{
                                Toast.makeText(Login.this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };

                LoginRequest r = new LoginRequest(Correo, Credencial, respuesta);
                RequestQueue cola = Volley.newRequestQueue(Login.this);
                cola.add(r);

            }
        });

    }
}