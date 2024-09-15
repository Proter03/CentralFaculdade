package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.domain.dto.GradeDTO;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.Grade;
import com.felipegabriel.centralfaculdade.repository.GradeCurricularRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

public class GradeCurricularService {
    private final GradeCurricularRepository gradeCurricularRepository;
    private final CursoService cursoService;
    private final DisciplinaService disciplinaService;
    private final TermoService termoService;

    public GradeCurricularService(Context context) {
        this.gradeCurricularRepository = new GradeCurricularRepository(context, Grade.class);
        this.cursoService = new CursoService(context);
        this.disciplinaService = new DisciplinaService(context);
        this.termoService = new TermoService(context);
    }

    public List<GradeDTO> getGradePorIdCurso(int idCurso) {
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        Curso curso = cursoService.getCursoById(idCurso);
        List<Grade> grade = gradeCurricularRepository.findAllByIdCurso(curso.getId());

        for (Grade item : grade) {
            Disciplina disciplina = disciplinaService.getDisciplinaById(item.getIdDisciplina());
            Termo termo = termoService.getTermoById(item.getIdTermo());

            gradeDTOS.add(getGradeDTO(item, curso, disciplina, termo));
        }

        return gradeDTOS;
    }

    public void criarGradeCurricular(Grade grade) {
        setaIdGrade(grade, (int)salvar(grade));
    }

    public long getAllRegistro() {
        return gradeCurricularRepository.countAll();
    }

    @NonNull
    private static GradeDTO getGradeDTO(Grade grade, Curso curso, Disciplina disciplina, Termo termo) {
        return new GradeDTO(grade.getId(), curso.getId(), curso.getDescricao(), disciplina.getId(), disciplina.getNome(), termo.getId(), termo.getDescricao(), disciplina.getHoras(), disciplina.getEmenda());
    }
    private Grade findByIdCurso(int idCurso) {
        return gradeCurricularRepository.findByIdCurso(idCurso).orElseThrow(() -> new RuntimeException("Nenhuma grade com esse curso foi encontrada. Curso: " + idCurso));
    }
    @NonNull
    private static Grade instanciaGrade(int idCurso, int idDisciplina, int idTermo) {
        Grade grade = new Grade();
        grade.setIdCurso(idCurso);
        grade.setIdTermo(idTermo);
        grade.setIdDisciplina(idDisciplina);

        return grade;
    }
    private void setaIdGrade(Grade grade, int id) {
        grade.setId(id);
    }
    private long salvar(Grade grade) {
        return gradeCurricularRepository.save(grade);
    }

    public Grade getGradePorIdCursoAndIdDisciplinaAndIdTermo(int idCurso, int idDisciplina, int idTermo) {
        return gradeCurricularRepository.findByIdCursoAndIdDisciplinaAndIdTermo(idCurso, idDisciplina, idTermo).orElse(null);
    }
}
