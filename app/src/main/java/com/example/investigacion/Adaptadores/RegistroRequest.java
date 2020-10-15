package com.example.investigacion.Adaptadores;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {
    private static final String ruta ="http://sleetiest-fogs.000webhostapp.com/Resgistro.php";
    private Map<String, String> parametros;

    public RegistroRequest(String Nombre, String NombreNegocio, String Correo, String Credencial, String Descripcion, Response.Listener<String> listener){
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("Nombre", Nombre+"");
        parametros.put("NombreNegocio", NombreNegocio+"");
        parametros.put("Correo", Correo+"");
        parametros.put("Credencial", Credencial+"");
        parametros.put("Descripcion", Descripcion+"");
    }

    protected Map<String, String> getParams(){
        return parametros;
    }

}
