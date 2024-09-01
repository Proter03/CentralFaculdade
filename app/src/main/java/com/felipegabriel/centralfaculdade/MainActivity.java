package com.felipegabriel.centralfaculdade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.repository.AlunoDAO;
import com.felipegabriel.centralfaculdade.repository.GenericDAO;

import java.time.LocalDate;
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
}