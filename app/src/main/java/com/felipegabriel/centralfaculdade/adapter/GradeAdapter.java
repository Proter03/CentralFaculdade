package com.felipegabriel.centralfaculdade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.dto.GradeDTO;

import java.util.List;

import lombok.Getter;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.ViewHolder> {

    List<GradeDTO> grade;

    public GradeAdapter(List<GradeDTO> grade) {
        this.grade = grade;
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDisciplina = itemView.findViewById(R.id.txtDisciplina_grade);
            txtCargaHoraria = itemView.findViewById(R.id.txtCargaHoraria_grade);
        }

        private final TextView txtDisciplina, txtCargaHoraria;
    }

    @NonNull
    @Override
    public GradeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.ViewHolder holder, int position) {
        GradeDTO gradeDTO = this.grade.get(position);
        holder.getTxtDisciplina().setText(gradeDTO.getNomeDisciplina());
        holder.getTxtCargaHoraria().setText(String.format("%.0f h", gradeDTO.getHoras()));
    }

    @Override
    public int getItemCount() {
        return grade.size();
    }
}
