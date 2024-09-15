package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.repository.DisciplinaRepository;

public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(Context context) {
        disciplinaRepository = new DisciplinaRepository(context, Disciplina.class);
    }

    public void criaDisciplina(String descricao) {
        montaDisciplina(descricao);
    }

    public Disciplina getDisciplinaByNome(String nome) {
        return findByNome(nome);
    }

    public Disciplina getDisciplinaById(int idDisciplina) {
        return getById(idDisciplina);
    }

    private Disciplina getById(int idDisciplina) {
        return disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new RuntimeException("Nao foi encontrado nenhuma disciplina com esse id: " + idDisciplina));
    }

    private void montaDisciplina(String nome) {
        Disciplina disciplina = instanciaDisciplina(nome);

        setaIdDisciplina(disciplina, (int) montaDisciplina(disciplina));
    }

    private long montaDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    private static void setaIdDisciplina(Disciplina disciplina, int id) {
        disciplina.setId(id);
    }

    private static Disciplina instanciaDisciplina(String nome) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);

        return disciplina;
    }

    private Disciplina findByNome(String nome) {
        return disciplinaRepository.findByNome(nome);
    }

}
