package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioAulaDTO;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioAula;
import com.felipegabriel.centralfaculdade.repository.HorarioAulaRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioAulaService {
    private final HorarioAulaRepository horarioAulaRepository;
    private final DisciplinaService disciplinaService;

    public HorarioAulaService(Context context) {
        this.horarioAulaRepository = new HorarioAulaRepository(context, HorarioAula.class);
        this.disciplinaService = new DisciplinaService(context);
    }

    public void criaHorarioAula(int idTermo, LocalTime horario, int idDisciplina, int idDiaDaSemana) {
        HorarioAula horarioAula = getHorarioAula(idTermo, horario, idDisciplina, idDiaDaSemana);

        horarioAulaRepository.save(horarioAula);
    }

    public List<HorarioAulaDTO> getHorariosAula(int idTermo) {
        List<HorarioAulaDTO> horarioAulaDTOS = new ArrayList<>();
        List<HorarioAula> horariosAulas = horarioAulaRepository.findByIdTermo(idTermo);

        for (HorarioAula horarioAula : horariosAulas) {
            String diaDaSemana = getDiaDaSemana(horarioAula);
            Disciplina disciplina = disciplinaService.getDisciplinaById(horarioAula.getIdDisciplina());

            horarioAulaDTOS.add(new HorarioAulaDTO(diaDaSemana, horarioAula.getHorario(), horarioAula.getIdDisciplina(), disciplina.getNome()));
        }

        return horarioAulaDTOS;
    }

    private static @NonNull String getDiaDaSemana(HorarioAula horarioAula) {
        String diaDaSemana = "DOMINGO";
        if (horarioAula.getIdDiaDaSemana() == 1) {
            diaDaSemana = "2° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 2) {
            diaDaSemana = "3° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 3) {
            diaDaSemana = "4° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 4) {
            diaDaSemana = "5° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 5) {
            diaDaSemana = "6° FEIRA";
        } else {
            diaDaSemana = "SÁBADO";
        }
        return diaDaSemana;
    }

    private static @NonNull HorarioAula getHorarioAula(int idTermo, LocalTime horario, int idDisciplina, int idDiaDaSemana) {
        HorarioAula horarioAula = new HorarioAula();
        horarioAula.setHorario(horario);
        horarioAula.setIdTermo(idTermo);
        horarioAula.setIdDisciplina(idDisciplina);
        horarioAula.setIdDiaDaSemana(idDiaDaSemana);
        return horarioAula;
    }
}
