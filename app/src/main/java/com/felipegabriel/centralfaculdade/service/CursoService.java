package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.repository.CursoRepository;

public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(Context context) {
        cursoRepository = new CursoRepository(context, Curso.class);
    }

    public void criaCurso(String descricao) {
        montaCruso(descricao);
    }

    private void montaCruso(String descricao) {
        Curso curso = instanciaCurso(descricao);

        setaIdCurso(curso, (int) montaCruso(curso));
    }

    private long montaCruso(Curso curso) {
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

    public Curso buscaCurso(String descricao) {
        return findByDescrica(descricao);
    }

    private Curso findByDescrica(String descricao) {
        return cursoRepository.findByDescricao(descricao);
    }
}
