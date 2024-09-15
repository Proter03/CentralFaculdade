package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.repository.CursoRepository;

public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(Context context) {
        cursoRepository = new CursoRepository(context, Curso.class);
    }

    public Curso getCursoByDescricao(String descricao) {
        return findByDescrica(descricao);
    }

    public Curso getCursoById(int idCurso) {
        return getById(idCurso);
    }

    public void criaCurso(String descricao) {
        montaCurso(descricao);
    }

    private Curso getById(int idCurso) {
        return cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso nao encontrado: " + idCurso));
    }

    private void montaCurso(String descricao) {
        Curso curso = instanciaCurso(descricao);

        setaIdCurso(curso, (int) salvar(curso));
    }

    private long salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    private static void setaIdCurso(Curso curso, int id) {
        curso.setId(id);
    }

    private static Curso instanciaCurso(String descricao) {
        Curso curso = new Curso();
        curso.setDescricao(descricao);

        return curso;
    }

    private Curso findByDescrica(String descricao) {
        return cursoRepository.findByDescricao(descricao);
    }
}
