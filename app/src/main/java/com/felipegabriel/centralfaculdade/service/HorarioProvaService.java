package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.domain.dto.HorarioProvaDTO;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioProva;
import com.felipegabriel.centralfaculdade.repository.HorarioProvaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioProvaService {
    private final HorarioProvaRepository horarioProvaRepository;
    private final DisciplinaService disciplinaService;
    private final TermoService termoService;

    public HorarioProvaService(Context context) {
        this.horarioProvaRepository = new HorarioProvaRepository(context, HorarioProva.class);
        this.disciplinaService = new DisciplinaService(context);
        this.termoService = new TermoService(context);
    }

    public List<HorarioProvaDTO> getHorariosProva(int idCurso, int idTermo) {
        List<HorarioProvaDTO> horarioProvaDTOS =new ArrayList<>();
        List<HorarioProva> horariosProvas = horarioProvaRepository.findByIdCursoAndIdTermo(idCurso, idTermo);

        for (HorarioProva horarioProva : horariosProvas) {
            Termo termo = termoService.getTermoById(horarioProva.getIdTermo());
            Disciplina disciplina = disciplinaService.getDisciplinaById(horarioProva.getIdDisciplina());
            horarioProvaDTOS.add(new HorarioProvaDTO(disciplina.getNome(), disciplina.getId(), horarioProva.getDataProva(), termo.getId(), termo.getDescricao()));
        }

        return horarioProvaDTOS;
    }

    public void criaHorarioProva(int idDisciplina, int idCurso, int idTermo, LocalDateTime dataProva) {
        HorarioProva horarioProva = getHorarioProva(idDisciplina, idCurso, idTermo, dataProva);

        horarioProvaRepository.save(horarioProva);
    }

    private static @NonNull HorarioProva getHorarioProva(int idDisciplina, int idCurso, int idTermo, LocalDateTime dataProva) {
        HorarioProva horarioProva = new HorarioProva();
        horarioProva.setDataProva(dataProva);
        horarioProva.setIdDisciplina(idDisciplina);
        horarioProva.setIdCurso(idCurso);
        horarioProva.setIdTermo(idTermo);
        return horarioProva;
    }
}
