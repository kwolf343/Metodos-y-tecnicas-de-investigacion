package com.example.investigacion.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.investigacion.R;

import java.util.List;

public class AdaptadorNegocio extends RecyclerView.Adapter<ViewHolderNegocio> implements View.OnClickListener {
    private List<Negocio> listaNegocios;
    private View.OnClickListener listener;
    public AdaptadorNegocio(List<Negocio> data){
        this.listaNegocios = data;
    }
    @NonNull
    @Override
    public ViewHolderNegocio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_negocios,parent,false);
        ViewHolderNegocio vhNegocios = new ViewHolderNegocio(vista);
        vista.setOnClickListener(this);
        return vhNegocios;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderNegocio holder, int position) {
        holder.getTxtNombre().setText(this.listaNegocios.get(position).getNombre());
        holder.getTxtCategoria().setText(this.listaNegocios.get(position).getCategoria());
        holder.getTxtUbicacion().setText(this.listaNegocios.get(position).getUbicacion());
    }

    @Override
    public int getItemCount() {
        return this.listaNegocios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
}
