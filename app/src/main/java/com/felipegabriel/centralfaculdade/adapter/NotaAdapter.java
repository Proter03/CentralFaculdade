package com.felipegabriel.centralfaculdade.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.dto.NotaDTO;

import java.util.List;

import lombok.Getter;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.ViewHolder> {

    private final List<NotaDTO> notas;

    private final int color_nota_alta = Color.parseColor("#007BFF");
    private final int color_nota_baixa = Color.parseColor("#DC3545");

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDisciplina = itemView.findViewById(R.id.txtDisciplina_nota);
            txtMedia = itemView.findViewById(R.id.txtMedia_nota);
        }

        private final TextView txtDisciplina, txtMedia;
    }

    public NotaAdapter(List<NotaDTO> notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nota_template, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotaDTO alunoDisciplinaNota = notas.get(position);
        holder.getTxtDisciplina().setText(alunoDisciplinaNota.getDisciplina());
        holder.getTxtMedia().setText(String.format("%.2f", alunoDisciplinaNota.getMedia()));
        holder.getTxtMedia().setTextColor(alunoDisciplinaNota.getMedia() < 7 ? color_nota_baixa : color_nota_alta);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }
}
