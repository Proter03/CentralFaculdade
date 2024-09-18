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
import com.felipegabriel.centralfaculdade.domain.dto.GradeDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.var;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.ViewHolder> {

    Map<String, List<GradeDTO>> grade;

    public GradeAdapter(List<GradeDTO> grade) {
        this.grade = grade.stream().collect(Collectors.groupingBy(GradeDTO::getDescricaoTermo));
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDisciplina = itemView.findViewById(R.id.txtDisciplina_grade);
            txtCargaHoraria = itemView.findViewById(R.id.txtCargaHoraria_grade);
            recyclerView = itemView.findViewById(R.id.rcDisciplinas_grade);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.addItemDecoration(new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.VERTICAL));
        }

        private final TextView txtDisciplina, txtCargaHoraria;
        private final RecyclerView recyclerView;
    }

    @NonNull
    @Override
    public GradeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.ViewHolder holder, int position) {
        var termo = getEntry(position);
        if (termo != null) {
            holder.getTxtDisciplina().setText(termo.getKey());

            DisciplinaAdapter adapter = new DisciplinaAdapter(termo.getValue());
            holder.getRecyclerView().setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return grade.size();
    }

    private Map.Entry<String, List<GradeDTO>> getEntry(int index) {
        List<Map.Entry<String, List<GradeDTO>>> entries = this.grade.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        return entries.get(index);
    }
}
