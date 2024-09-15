package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.domain.dto.GradeDTO;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.Grade;
import com.felipegabriel.centralfaculdade.repository.GradeRepository;

import lombok.NonNull;

public class GradeService {
    private final GradeRepository gradeRepository;
    private final CursoService cursoService;
    private final DisciplinaService disciplinaService;
    private final TermoService termoService;

    public GradeService(Context context) {
        this.gradeRepository = new GradeRepository(context, Grade.class);
        this.cursoService = new CursoService(context);
        this.disciplinaService = new DisciplinaService(context);
        this.termoService = new TermoService(context);
    }

    public GradeDTO getGradePorIdCurso(int idCurso) {
        Curso curso = cursoService.getCursoById(idCurso);
        Grade grade = findByIdCurso(curso.getId());
        Disciplina disciplina = disciplinaService.getDisciplinaById(grade.getIdDisciplina());
        Termo termo = termoService.getTermoById(grade.getIdTermo());

        return getGradeDTO(grade, curso, disciplina, termo);
    }

    @NonNull
    private static GradeDTO getGradeDTO(Grade grade, Curso curso, Disciplina disciplina, Termo termo) {
        return new GradeDTO(grade.getId(), curso.getId(), curso.getDescricao(), disciplina.getId(), disciplina.getNome(), termo.getId(), termo.getDescricao(), grade.getHoras(), grade.getEmenda());
    }
    private Grade findByIdCurso(int idCurso) {
        return gradeRepository.findByIdCurso(idCurso).orElseThrow(() -> new RuntimeException("Nenhuma grade com esse curso foi encontrada. Curso: " + idCurso));
    }
    @NonNull
    private static Grade instanciaGrade(int idCurso, int idDisciplina, int idTermo, Double horas, String emenda) {
        Grade grade = new Grade();
        grade.setIdCurso(idCurso);
        grade.setIdTermo(idTermo);
        grade.setIdDisciplina(idDisciplina);
        grade.setHoras(horas);
        grade.setEmenda(emenda);

        return grade;
    }
    private void setaIdGrade(Grade grade, int id) {
        grade.setId(id);
    }
    private long salvar(Grade grade) {
        return gradeRepository.save(grade);
    }
}
