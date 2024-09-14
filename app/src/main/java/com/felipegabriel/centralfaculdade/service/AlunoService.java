package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.repository.AlunoRepository;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(Context context) {
        alunoRepository = new AlunoRepository(context, Aluno.class);
    }


    public void criaAluno(int idUser, String nome) {
        montaAluno(idUser, nome);
    }

    public Aluno buscaAluno(int idUser) {
        return findByIdUser(idUser);
    }

    private Aluno findByIdUser(int idUser) {
        return alunoRepository.findByIdUser(idUser).orElse(null);
    }

    private void montaAluno(int idUser, String nome) {
        Aluno aluno = instanciaAluno(idUser, nome);

        setaIdAluno(aluno, (int) montaAluno(aluno));
    }

    private static void setaIdAluno(Aluno aluno, int id) {
        aluno.setId(id);
    }

    private long montaAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @NonNull
    private static Aluno instanciaAluno(int idUser, String nome) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdUsuario(idUser);
        aluno.setDataCadastro(LocalDate.now());
        return aluno;
    }
}
