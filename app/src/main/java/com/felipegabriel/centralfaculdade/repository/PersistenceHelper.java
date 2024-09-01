package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper {
    private static PersistenceHelper instance;
    private final GenericDAO<?> instanciaTable;
    private static final String NOME_BANCO = "Central_Faculdade";
    private static final int VERSAO = 1;

    public PersistenceHelper(Context context, GenericDAO<?> instanciaTable) {
        super(context, NOME_BANCO, null, VERSAO);

        this.instanciaTable = instanciaTable;
    }

    public static PersistenceHelper getInstance(Context context, GenericDAO<?> instanciaTable) {
        if(instance == null) {
            instance = new PersistenceHelper(context, instanciaTable);
        }

        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(instanciaTable.scriptCreate());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(instanciaTable.scriptDrop());
        onCreate(db);
    }
}
