package com.felipegabriel.centralfaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Docente;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.domain.Usuario;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaTermo;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.CursoDisciplina;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.Grade;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DisciplinaTermoProva;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DocenteDisciplinaTermo;
import com.felipegabriel.centralfaculdade.repository.GenericDatabase;
import com.felipegabriel.centralfaculdade.service.AlunoDisciplinaNotaService;
import com.felipegabriel.centralfaculdade.service.AlunoService;
import com.felipegabriel.centralfaculdade.service.CursoService;
import com.felipegabriel.centralfaculdade.service.DisciplinaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity extends AppCompatActivity {
    private GenericDatabase<Usuario> tableUsuario;
    private DisciplinaService disciplinaService;
    private AlunoService alunoService;
    private CursoService cursoService;
    private AlunoDisciplinaNotaService alunoDisciplinaNotaService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alunoService = new AlunoService(this);
        cursoService = new CursoService(this);
        disciplinaService = new DisciplinaService(this);
        alunoDisciplinaNotaService = new AlunoDisciplinaNotaService(this);

        init();
    }

    private void init() {
        getGenericDatabase();

        criaUsuario();

        criaAluno();

        criaCurso();

        criaDisciplina();
        
        criaNotaNota();
    }

    private void criaNotaNota() {
        Curso curso = cursoService.getCursoByDescricao("Ciência da Computação");
        Aluno aluno = alunoService.buscaAluno(Sessao.getId());
        Disciplina disciplina = disciplinaService.getDisciplinaByNome("Empreendedorismo");

        if (curso == null || aluno == null || disciplina == null) {
            throw new RuntimeException("Erro criar nota");
        }

        AlunoDisciplinaNota alunoDisciplinaNota = alunoDisciplinaNotaService.getIdAlunoAndIdDisciplina(aluno.getId(), disciplina.getId(), curso.getId());
        if (alunoDisciplinaNota == null) {
            alunoDisciplinaNotaService.criaAlunoDisciplinaNota(aluno.getId(), disciplina.getId(), curso.getId(), 8);
        }
    }

    private void criaDisciplina() {
        Disciplina disciplina = disciplinaService.getDisciplinaByNome("Empreendedorismo");
        if (disciplina == null) {
            disciplinaService.criaDisciplina("Empreendedorismo");
        }
    }

    private void criaCurso() {
        Curso curso = cursoService.getCursoByDescricao("Ciência da Computação");
        if (curso == null) {
            cursoService.criaCurso("Ciência da Computação");
        }
    }

    private void criaAluno() {
        if (alunoService.buscaAluno(Sessao.getId()) == null) {
            alunoService.criaAluno(Sessao.getId(), "Usuario");
        }
    }

    private void criaUsuario() {
        Usuario usuario = tableUsuario.checkUser("user", "user");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setUsuario("user");
            usuario.setSenha("user");
            long id = tableUsuario.save(usuario);
            usuario.setId((int) id);
        }

        Sessao.setUser(usuario.getId(), usuario.getUsuario(), usuario.getSenha());
    }

    private void getGenericDatabase() {

        try {
            try (GenericDatabase<Aluno> tableAluno = new GenericDatabase<>(this, Aluno.class)) {
                tableAluno.getWritableDatabase();
            }

            try (GenericDatabase<Curso> tableCurso = new GenericDatabase<>(this, Curso.class)) {
                tableCurso.getWritableDatabase();
            }

            try (GenericDatabase<Disciplina> tableDisciplina = new GenericDatabase<>(this, Disciplina.class)) {
                tableDisciplina.getWritableDatabase();
            }

            try (GenericDatabase<Docente> tableDocente = new GenericDatabase<>(this, Docente.class)) {
                tableDocente.getWritableDatabase();
            }

            try (GenericDatabase<Termo> tableTermo = new GenericDatabase<>(this, Termo.class)) {
                tableTermo.getWritableDatabase();
            }

            try (GenericDatabase<AlunoDisciplinaTermo> tableAlunoDisciplinaTermo = new GenericDatabase<>(this, AlunoDisciplinaTermo.class)) {
                tableAlunoDisciplinaTermo.getWritableDatabase();
            }

            try (GenericDatabase<CursoDisciplina> tableCursoDisciplina = new GenericDatabase<>(this, CursoDisciplina.class)) {
                tableCursoDisciplina.getWritableDatabase();
            }

            try (GenericDatabase<Grade> tableCursoDisciplinaTermo = new GenericDatabase<>(this, Grade.class)) {
                tableCursoDisciplinaTermo.getWritableDatabase();
            }

            try (GenericDatabase<DisciplinaTermoProva> tableDisciplinaTermoProva = new GenericDatabase<>(this, DisciplinaTermoProva.class)) {
                tableDisciplinaTermoProva.getWritableDatabase();
            }

            try (GenericDatabase<DocenteDisciplinaTermo> tableDocenteDisciplinaTermo = new GenericDatabase<>(this, DocenteDisciplinaTermo.class)) {
                tableDocenteDisciplinaTermo.getWritableDatabase();
            }

            try (GenericDatabase<AlunoDisciplinaNota> tableAlunoDisciplinaNota = new GenericDatabase<>(this, AlunoDisciplinaNota.class)) {
                tableAlunoDisciplinaNota.getWritableDatabase();
            }

            tableUsuario = new GenericDatabase<>(this, Usuario.class);
            tableUsuario.getWritableDatabase();


        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public void onClickNotas(View view) {
        Intent intent = new Intent(this, Notas.class);
        startActivity(intent);
    }
}