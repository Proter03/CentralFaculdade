package com.felipegabriel.centralfaculdade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.domain.Sessao;
import com.felipegabriel.centralfaculdade.domain.Usuario;
import com.felipegabriel.centralfaculdade.repository.AlunoRepository;
import com.felipegabriel.centralfaculdade.repository.GenericDatabase;
import com.felipegabriel.centralfaculdade.service.AlunoService;

public class Notas extends AppCompatActivity {
    private AlunoService alunoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        setSupportActionBar(findViewById(R.id.toolbar_notas));

        alunoService = new AlunoService(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Aluno aluno = getAluno();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Aluno getAluno() {

        Aluno aluno = alunoService.buscaAluno(Sessao.getId());

        if (aluno == null) {
            throw new RuntimeException("Aluno nao encontrado");
        }

        return aluno;
    }
}