package com.felipegabriel.centralfaculdade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioAulaDTO;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class AulaAdapter extends RecyclerView.Adapter<AulaAdapter.ViewHolder> {

    List<HorarioAulaDTO> aulas;

    public AulaAdapter(List<HorarioAulaDTO> aulas) {
        this.aulas = aulas.stream().sorted(Comparator.comparing(HorarioAulaDTO::getHorario)).collect(Collectors.toList());
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHorario = itemView.findViewById(R.id.txtHorario_aula);
            txtAula = itemView.findViewById(R.id.txtAula_aula);
        }

        private final TextView txtHorario, txtAula;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aula_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder holder, int position) {
        HorarioAulaDTO aula = aulas.get(position);
        holder.getTxtHorario().setText(aula.getHorario().format(DateTimeFormatter.ofPattern("HH:mm")));
        holder.getTxtAula().setText(aula.getNomeDisciplina());
    }

    @Override
    public int getItemCount() {
        return aulas.size();
    }
}
