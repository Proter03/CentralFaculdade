package com.felipegabriel.centralfaculdade.repository;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public abstract class GenericDAO<T> {
    public SQLiteDatabase dataBase = null;
    public String nomeTabela = "";
    private final String NOME_TABELA = "";

    public abstract void salvar(T value);

    public abstract T findById(int id);

    public abstract List<T> findAll();

    public abstract String scriptCreate();

    public abstract String scriptDrop();
    public void drop() {
        dataBase.execSQL(scriptDrop());
    };

}
