package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import androidx.annotation.NonNull;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;
import com.felipegabriel.centralfaculdade.repository.AlunoDisciplinaNotaRepository;

import java.util.List;

public class AlunoDisciplinaNotaService {
    private final AlunoDisciplinaNotaRepository alunoDisciplinaNotaRepository;

    public AlunoDisciplinaNotaService(Context context) {
        this.alunoDisciplinaNotaRepository = new AlunoDisciplinaNotaRepository(context, AlunoDisciplinaNota.class);
    }

    public List<AlunoDisciplinaNota> getAllNotas(int idAluno) {
        return this.alunoDisciplinaNotaRepository.findByIdAluno(idAluno);
    }

    public AlunoDisciplinaNota getIdAlunoAndIdDisciplina(int idAluno, int idDisciplina, int idCurso) {
        return findByIdAlunoAndIdDisciplina(idAluno, idDisciplina, idCurso);
    }

    public AlunoDisciplinaNota criaAlunoDisciplinaNota(int idAluno, int idDisciplina, int idCurso, double mediaFinal) {
        AlunoDisciplinaNota alunoDisciplinaNota = instanciaAlunoDisciplinaNota(idAluno, idDisciplina, idCurso, mediaFinal);

        setaIdCurso(alunoDisciplinaNota, (int) salvar(alunoDisciplinaNota));

        return alunoDisciplinaNota;
    }

    private void setaIdCurso(AlunoDisciplinaNota alunoDisciplinaNota, int id) {
        alunoDisciplinaNota.setId(id);
    }

    private long salvar(AlunoDisciplinaNota alunoDisciplinaNota) {
        return alunoDisciplinaNotaRepository.save(alunoDisciplinaNota);
    }

    @NonNull
    private static AlunoDisciplinaNota instanciaAlunoDisciplinaNota(int idAluno, int idDisciplina, int idCurso, double mediaFinal) {
        AlunoDisciplinaNota alunoDisciplinaNota = new AlunoDisciplinaNota();
        alunoDisciplinaNota.setIdDisciplina(idDisciplina);
        alunoDisciplinaNota.setIdAluno(idAluno);
        alunoDisciplinaNota.setMediaFinal(mediaFinal);
        alunoDisciplinaNota.setIdCurso(idCurso);
        return alunoDisciplinaNota;
    }

    private AlunoDisciplinaNota findByIdAlunoAndIdDisciplina(int idAluno, int idDisciplina, int idCurso) {
        return alunoDisciplinaNotaRepository.findByIdAlunoAndIdDisciplina(idAluno, idDisciplina, idCurso).orElse(null);
    }
}
