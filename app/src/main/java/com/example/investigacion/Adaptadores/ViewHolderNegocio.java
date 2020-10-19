package com.example.investigacion.Adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.investigacion.R;

public class ViewHolderNegocio extends RecyclerView.ViewHolder{

    private TextView txtNombre;
    private TextView txtPropietario;
    private TextView txtCategoria;
    private TextView txtUbicacion;
    private TextView txtLat;
    private TextView txtLong;
    public ViewHolderNegocio(@NonNull View itemView) {
        super(itemView);
        this.txtNombre = itemView.findViewById(R.id.lblNombre);
        this.txtCategoria = itemView.findViewById(R.id.lblCategoria);
        this.txtUbicacion = itemView.findViewById(R.id.lblUbicacion);
    }

    public TextView getTxtNombre() {
        return txtNombre;
    }

    public TextView getTxtCategoria() {
        return txtCategoria;
    }

    public TextView getTxtUbicacion() {
        return txtUbicacion;
    }
}
