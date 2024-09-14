package com.felipegabriel.centralfaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Curso;
import com.felipegabriel.centralfaculdade.domain.Disciplina;
import com.felipegabriel.centralfaculdade.domain.Docente;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.domain.Turma;
import com.felipegabriel.centralfaculdade.domain.Usuario;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaTermo;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.CursoDisciplina;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.CursoDisciplinaTermo;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DisciplinaTermoProva;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DisciplinaTurma;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.DocenteDisciplinaTermo;
import com.felipegabriel.centralfaculdade.repository.AlunoRepository;
import com.felipegabriel.centralfaculdade.repository.GenericDatabase;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity extends AppCompatActivity {
    private GenericDatabase<Usuario> tableUsuario;
    private GenericDatabase<Curso> tableCurso;
    private GenericDatabase<Aluno> tableAluno;
    private AlunoRepository alunoRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alunoRepository = new AlunoRepository(this, Aluno.class);

        init();
    }

    private void init() {
        getGenericDatabase();

        criaUsuario();

        criaAluno();
    }

    private void criaAluno() {
        if (alunoRepository.findByIdUser(Sessao.getId()) == null) {
            Aluno aluno = new Aluno();

            aluno.setNome("Usuario");
            aluno.setDataCadastro(LocalDate.now());
            aluno.setIdUsuario(Sessao.getId());

            tableAluno.save(aluno);
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
        GenericDatabase<Disciplina> tableDisciplina;
        GenericDatabase<Docente> tableDocente;
        GenericDatabase<Termo> tableTermo;
        GenericDatabase<Turma> tableTurma;
        GenericDatabase<AlunoDisciplinaTermo> tableAlunoDisciplinaTermo;
        GenericDatabase<CursoDisciplina> tableCursoDisciplina;
        GenericDatabase<CursoDisciplinaTermo> tableCursoDisciplinaTermo;
        GenericDatabase<DisciplinaTermoProva> tableDisciplinaTermoProva;
        GenericDatabase<DocenteDisciplinaTermo> tableDocenteDisciplinaTermo;
        GenericDatabase<DisciplinaTurma> tableDisciplinaTurma;
        try {
            tableAluno = new GenericDatabase<>(this, Aluno.class);
            tableAluno.getWritableDatabase();

            tableCurso = new GenericDatabase<>(this, Curso.class);
            tableCurso.getWritableDatabase();

            tableDisciplina = new GenericDatabase<>(this, Disciplina.class);
            tableDisciplina.getWritableDatabase();

            tableDocente = new GenericDatabase<>(this, Docente.class);
            tableDocente.getWritableDatabase();

            tableTermo = new GenericDatabase<>(this, Termo.class);
            tableTermo.getWritableDatabase();

            tableTurma = new GenericDatabase<>(this, Turma.class);
            tableTurma.getWritableDatabase();

            tableAlunoDisciplinaTermo = new GenericDatabase<>(this, AlunoDisciplinaTermo.class);
            tableAlunoDisciplinaTermo.getWritableDatabase();

            tableCursoDisciplina = new GenericDatabase<>(this, CursoDisciplina.class);
            tableCursoDisciplina.getWritableDatabase();

            tableCursoDisciplinaTermo = new GenericDatabase<>(this, CursoDisciplinaTermo.class);
            tableCursoDisciplinaTermo.getWritableDatabase();

            tableDisciplinaTermoProva = new GenericDatabase<>(this, DisciplinaTermoProva.class);
            tableDisciplinaTermoProva.getWritableDatabase();

            tableDisciplinaTurma = new GenericDatabase<>(this, DisciplinaTurma.class);
            tableDisciplinaTurma.getWritableDatabase();

            tableDocenteDisciplinaTermo = new GenericDatabase<>(this, DocenteDisciplinaTermo.class);
            tableDocenteDisciplinaTermo.getWritableDatabase();

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