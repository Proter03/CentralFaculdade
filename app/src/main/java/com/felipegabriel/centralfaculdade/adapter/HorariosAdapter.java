package com.felipegabriel.centralfaculdade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioAulaDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.var;

public class HorariosAdapter extends RecyclerView.Adapter<HorariosAdapter.ViewHolder> {

    Map<String, List<HorarioAulaDTO>> horarios;

    public HorariosAdapter(List<HorarioAulaDTO> horarios) {
        this.horarios = horarios.stream().collect(Collectors.groupingBy(HorarioAulaDTO::getDiaDaSemana));
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDiaSemana = itemView.findViewById(R.id.txtDiaSemana_horarios);
            recyclerView = itemView.findViewById(R.id.rcAulas_horarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.VERTICAL));
        }

        private final TextView txtDiaSemana;
        private final RecyclerView recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horarios_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        var aula = getEntry(position);
        if (aula != null) {
            holder.txtDiaSemana.setText(aula.getKey());

            AulaAdapter adapter = new AulaAdapter(aula.getValue());
            holder.getRecyclerView().setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }

    private Map.Entry<String, List<HorarioAulaDTO>> getEntry(int index) {
        List<Map.Entry<String, List<HorarioAulaDTO>>> entries = this.horarios.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        return entries.get(index);
    }
}
