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
        this.txtPropietario = itemView.findViewById(R.id.lblPropietario);
        this.txtCategoria = itemView.findViewById(R.id.lblCategoria);
        this.txtUbicacion = itemView.findViewById(R.id.lblUbicacion);
        this.txtLat = itemView.findViewById(R.id.lblLat);
        this.txtLong = itemView.findViewById(R.id.lblLong);
    }

    public TextView getTxtNombre() {
        return txtNombre;
    }

    public TextView getTxtPropietario() {
        return txtPropietario;
    }

    public TextView getTxtCategoria() {
        return txtCategoria;
    }

    public TextView getTxtUbicacion() {
        return txtUbicacion;
    }

    public TextView getTxtLat() {
        return txtLat;
    }

    public TextView getTxtLong() {
        return txtLong;
    }
}
