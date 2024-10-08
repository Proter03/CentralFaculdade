package com.felipegabriel.centralfaculdade.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felipegabriel.centralfaculdade.R;
import com.felipegabriel.centralfaculdade.adapter.NotaAdapter;
import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.dto.NotaDTO;
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
        List<AlunoDisciplinaNota> alunoDisciplinaNotas = alunoDisciplinaNotaService.getAllNotas(aluno.getId());

        List<NotaDTO> notasDTO = new ArrayList<>();

        for (AlunoDisciplinaNota alunoDisciplinaNota : alunoDisciplinaNotas) {
            Disciplina disciplina = disciplinaService.getDisciplinaById(alunoDisciplinaNota.getIdDisciplina());
            notasDTO.add(new NotaDTO(disciplina.getNome(), alunoDisciplinaNota.getMediaFinal()));
        }

        recyclerView = findViewById(R.id.rcNotas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NotaAdapter adapter = new NotaAdapter(notasDTO);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private Aluno getAluno() {
        Aluno aluno = alunoService.buscaAluno(Sessao.getId());

        if (aluno == null) {
            throw new RuntimeException("Aluno nao encontrado");
        }

        return aluno;
    }
}