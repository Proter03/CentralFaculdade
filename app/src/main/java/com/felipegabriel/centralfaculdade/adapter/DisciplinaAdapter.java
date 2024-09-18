package com.felipegabriel.centralfaculdade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.domain.dto.GradeDTO;

import java.util.List;

import lombok.Getter;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.ViewHolder> {

    List<GradeDTO> disciplinas;

    public DisciplinaAdapter(List<GradeDTO> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDisciplina = itemView.findViewById(R.id.txtDisciplina_disciplina);
            txtCargaHoraria = itemView.findViewById(R.id.txtCargaHoraria_disciplina);
            txtEmenda = itemView.findViewById(R.id.txtEmenda_disciplina);
            linearLayout = itemView.findViewById(R.id.disciplina_template);
            llEmenda = itemView.findViewById(R.id.emenda_disciplina);

            linearLayout.setOnClickListener(this::onClick);
        }

        private boolean visible = false;

        private final TextView txtDisciplina, txtCargaHoraria, txtEmenda;
        private final LinearLayout linearLayout, llEmenda;

        private void onClick(View view) {
            if (visible) {
                llEmenda.setVisibility(View.GONE);
                visible = false;
            } else {
                llEmenda.setVisibility(View.VISIBLE);
                visible = true;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disciplina_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GradeDTO disciplina = disciplinas.get(position);
        holder.getTxtDisciplina().setText(disciplina.getNomeDisciplina());
        holder.getTxtCargaHoraria().setText(String.format("%.0f h", disciplina.getHoras()));
        holder.getTxtEmenda().setText(disciplina.getEmenda());
    }

    @Override
    public int getItemCount() {
        return disciplinas.size();
    }
}
