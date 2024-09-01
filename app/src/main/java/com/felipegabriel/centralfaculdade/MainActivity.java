package com.felipegabriel.centralfaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.repository.AlunoDAO;
import com.felipegabriel.centralfaculdade.repository.GenericDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GenericDAO<Aluno> alunoDAO = new AlunoDAO(getBaseContext());

        Aluno alunoCriado = alunoDAO.findById(1);
        List<Aluno> alunos = alunoDAO.findAll();
    }

    public void onClickNotas(View view) {
        Intent intent = new Intent(this, Notas.class);
        startActivity(intent);
    }
}