package com.example.investigacion;

import com.example.investigacion.Adaptadores.Negocio;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceVendedores {
    public static List<Negocio> ListaNegocios(String string){
        ArrayList<Negocio> neg = new ArrayList<>();
        ArrayList<String> cadenas = new ArrayList<>();
        String[] parts = string.split(";");
        if(parts[0]!=""){
            for(int i=0;i<parts.length;i++){
                cadenas.add(parts[i]);
            }
        }
        for(int i=0;i<cadenas.size();i++){
            String[] parts2 = cadenas.get(i).split("-");
            Negocio n = new Negocio();
            n.setNombre(parts2[0]);
            n.setPropietario(parts2[1]);
            n.setInformacion(parts2[2]);
            n.setCategoria(parts2[3]);
            n.setUbicacion(parts2[4]);
            n.setCoordenada_Latitud(parts2[5]);
            n.setCoordenada_Longitud(parts2[6]);
            neg.add(n);
        }
        return neg;
    }
}
