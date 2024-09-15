package com.felipegabriel.centralfaculdade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;

import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaViewHolder> {

    List<AlunoDisciplinaNota> notas;

    public NotaAdapter(List<AlunoDisciplinaNota> notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lista = LayoutInflater.from(parent.getContext()).inflate(R.layout.nota_template, parent, false);
        return new NotaViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        AlunoDisciplinaNota alunoDisciplinaNota = notas.get(position);
        holder.txtNota.setText(String.format("%.2f", alunoDisciplinaNota.getMediaFinal()));
        holder.txtDisciplina.setText(String.format("%d", alunoDisciplinaNota.getIdDisciplina()));
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }
}
