package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Aluno;
import com.felipegabriel.centralfaculdade.repository.GenericDatabase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AlunoService {
    private GenericDatabase<Aluno> dbHelper;

    public AlunoService(GenericDatabase<Aluno> dbHelper, Context context) {
        this.dbHelper = dbHelper;
        this.dbHelper = new GenericDatabase<>(context, Aluno.class);

        dbHelper.getWritableDatabase();
    }
}
