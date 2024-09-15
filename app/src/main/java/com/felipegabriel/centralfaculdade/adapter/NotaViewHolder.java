package com.felipegabriel.centralfaculdade.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;

public class NotaViewHolder extends RecyclerView.ViewHolder {
    public NotaViewHolder(@NonNull View itemView) {
        super(itemView);

        txtDisciplina = itemView.findViewById(R.id.txtDisciplina_nota);
        txtNota = itemView.findViewById(R.id.txtMedia_nota);
    }

    TextView txtDisciplina, txtNota;
}
