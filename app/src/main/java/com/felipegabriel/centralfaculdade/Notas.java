package com.felipegabriel.centralfaculdade;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.dto.NotaDTO;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;
import com.felipegabriel.centralfaculdade.service.AlunoDisciplinaNotaService;
import com.felipegabriel.centralfaculdade.service.AlunoService;
import com.felipegabriel.centralfaculdade.service.CursoService;
import com.felipegabriel.centralfaculdade.service.DisciplinaService;

public class Notas extends AppCompatActivity {
    private AlunoService alunoService;
    private DisciplinaService disciplinaService;
    private CursoService cursoService;
    private AlunoDisciplinaNotaService alunoDisciplinaNotaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        setSupportActionBar(findViewById(R.id.toolbar_notas));

        alunoService = new AlunoService(this);
        cursoService = new CursoService(this);
        disciplinaService = new DisciplinaService(this);
        alunoDisciplinaNotaService = new AlunoDisciplinaNotaService(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Aluno aluno = getAluno();
        Curso curso = getCurso();
        Disciplina disciplina = getDisciplina();

        AlunoDisciplinaNota alunoDisciplinaNota = getAlunoDisciplinaNota(aluno.getId(), curso.getId(), disciplina.getId());
        NotaDTO notaDTO = new NotaDTO(disciplina.getNome(), alunoDisciplinaNota.getMediaFinal());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Disciplina getDisciplina() {
        return disciplinaService.getDisciplinaByNome("ALGORITMOS E ESTRUTURAS DE DADOS I");
    }

    private Curso getCurso() {
        return cursoService.getCursoByDescricao("Ciência da Computação");
    }

    private Aluno getAluno() {
        Aluno aluno = alunoService.buscaAluno(Sessao.getId());

        if (aluno == null) {
            throw new RuntimeException("Aluno nao encontrado");
        }

        return aluno;
    }

    private AlunoDisciplinaNota getAlunoDisciplinaNota(int idAluno, int idCurso, int idDisciplina) {
        return alunoDisciplinaNotaService.getIdAlunoAndIdDisciplina(idAluno, idDisciplina, idCurso);
    }
}