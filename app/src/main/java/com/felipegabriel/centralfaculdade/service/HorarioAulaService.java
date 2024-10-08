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

    public void criaHorarioAula(int idTermo, LocalTime horario, int idDisciplina, int idDiaDaSemana, int idCurso) {
        HorarioAula horarioAula = getHorarioAula(idTermo, horario, idDisciplina, idDiaDaSemana, idCurso);

        horarioAulaRepository.save(horarioAula);
    }

    public List<HorarioAulaDTO> getHorariosAula(int idTermo, int idCurso) {
        List<HorarioAulaDTO> horarioAulaDTOS = new ArrayList<>();
        List<HorarioAula> horariosAulas = horarioAulaRepository.findByIdTermoAndIdCurso(idTermo, idCurso);

        for (HorarioAula horarioAula : horariosAulas) {
            String diaDaSemana = getDiaDaSemana(horarioAula);
            Disciplina disciplina = disciplinaService.getDisciplinaById(horarioAula.getIdDisciplina());

            horarioAulaDTOS.add(new HorarioAulaDTO(diaDaSemana, horarioAula.getHorario(), horarioAula.getIdDisciplina(), disciplina.getNome()));
        }

        return horarioAulaDTOS;
    }

    private static @NonNull String getDiaDaSemana(HorarioAula horarioAula) {
        if (horarioAula.getIdDiaDaSemana() == 1) {
            return "2° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 2) {
            return "3° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 3) {
            return "4° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 4) {
            return "5° FEIRA";
        } else if (horarioAula.getIdDiaDaSemana() == 5) {
            return "6° FEIRA";
        } else {
            return "SÁBADO";
        }
    }

    private static @NonNull HorarioAula getHorarioAula(int idTermo, LocalTime horario, int idDisciplina, int idDiaDaSemana, int idCurso) {
        HorarioAula horarioAula = new HorarioAula();
        horarioAula.setHorario(horario);
        horarioAula.setIdTermo(idTermo);
        horarioAula.setIdDisciplina(idDisciplina);
        horarioAula.setIdDiaDaSemana(idDiaDaSemana);
        horarioAula.setIdCurso(idCurso);
        return horarioAula;
    }
}
