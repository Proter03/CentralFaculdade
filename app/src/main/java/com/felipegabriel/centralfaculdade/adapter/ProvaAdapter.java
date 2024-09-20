package com.felipegabriel.centralfaculdade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioProvaDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Getter;

public class ProvaAdapter extends RecyclerView.Adapter<ProvaAdapter.ViewHolder> {

    List<HorarioProvaDTO> provas;

    public ProvaAdapter(List<HorarioProvaDTO> provas) {
        this.provas = provas;
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDataHora = itemView.findViewById(R.id.txtDataHora_prova);
            txtDisciplina = itemView.findViewById(R.id.txtDisciplina_prova);
        }

        private final TextView txtDataHora, txtDisciplina;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prova_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HorarioProvaDTO prova = provas.get(position);
        holder.getTxtDataHora().setText(prova.getDataProva().format(DateTimeFormatter.ofPattern("dd/MM - HH:mm")));
        holder.getTxtDisciplina().setText(prova.getNomeDisciplina());
    }

    @Override
    public int getItemCount() {
        return provas.size();
    }
}
