package com.felipegabriel.centralfaculdade.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.adapter.NotaAdapter;
import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;
import com.felipegabriel.centralfaculdade.service.AlunoDisciplinaNotaService;
import com.felipegabriel.centralfaculdade.service.AlunoService;
import com.felipegabriel.centralfaculdade.service.CursoService;
import com.felipegabriel.centralfaculdade.service.DisciplinaService;

import java.util.ArrayList;
import java.util.List;

public class NotasActivity extends AppCompatActivity {
    private AlunoService alunoService;
    private DisciplinaService disciplinaService;
    private CursoService cursoService;
    private AlunoDisciplinaNotaService alunoDisciplinaNotaService;

    RecyclerView recyclerView;

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

        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void init() {
        Aluno aluno = getAluno();
        Curso curso = getCurso();
        Disciplina disciplina = getDisciplina();

        AlunoDisciplinaNota alunoDisciplinaNota = getAlunoDisciplinaNota(aluno.getId(), curso.getId(), disciplina.getId());

        List<AlunoDisciplinaNota> notas = new ArrayList<>();
        notas.add(alunoDisciplinaNota);
        notas.add(alunoDisciplinaNota);
        notas.add(alunoDisciplinaNota);
        notas.add(alunoDisciplinaNota);
        notas.add(alunoDisciplinaNota);
        notas.add(alunoDisciplinaNota);
        notas.add(alunoDisciplinaNota);

        recyclerView = findViewById(R.id.rcNotas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NotaAdapter adapter = new NotaAdapter(notas);
        recyclerView.setAdapter(adapter);
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