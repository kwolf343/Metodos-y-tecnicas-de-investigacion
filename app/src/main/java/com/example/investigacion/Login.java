package com.example.investigacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.investigacion.Adaptadores.Negocio;
import com.example.investigacion.Adaptadores.RegistroRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private int identificador;
    private SharedPreferences vendedores;
    private List<Negocio> datosDeNegocio = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button Login = (Button) findViewById(R.id.btnLoginIngresar);
        final TextView Registro1 = (TextView) findViewById(R.id.txvRegistro);
        final EditText UsuarioL = (EditText) findViewById(R.id.LoginUser);
        final EditText CredencialL = (EditText) findViewById(R.id.LoginPass);
        this.identificador = Integer.parseInt(getIntent().getStringExtra("id"));
        this.vendedores = getSharedPreferences(com.example.investigacion.Registro.ARCHIVO,MODE_PRIVATE);
        datosDeNegocio = SharedPreferenceVendedores.ListaNegocios(vendedores.getString(Registro.KEY,""));

        Registro1.setOnClickListener(new View.OnClickListener() {
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
                if(identificador == 1){
                    Response.Listener<String> respuesta = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonRespuesta = new JSONObject(response);
                                boolean ok = jsonRespuesta.getBoolean("success");
                                if(ok == true){
                                    Toast.makeText(Login.this, "Datos Correctos", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Login.this, Lista_Negocios.class);
                                    startActivity(i);
                                    finish();
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
                else{
                    boolean val=false;
                    int numerador=0;
                    for(int i=0;i<datosDeNegocio.size();i++){
                        if(datosDeNegocio.get(i).getNombre().equals(Correo) && datosDeNegocio.get(i).getClave().equals(Credencial)){
                            numerador=i;
                            val=true;
                        }
                    }
                    if(val==true){
                        Intent i = new Intent(Login.this, Vendedor.class);
                        i.putExtra("numerador",Integer.toString(numerador));
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(Login.this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}