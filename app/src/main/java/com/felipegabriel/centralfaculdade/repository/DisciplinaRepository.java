package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.Disciplina;

public class DisciplinaRepository extends GenericDatabase<Disciplina> {
    private final String tableName = Disciplina.class.getSimpleName();
    public DisciplinaRepository(Context context, Class<Disciplina> clazz) {
        super(context, clazz);
    }

    public Disciplina findByNome(String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        Disciplina disciplina = null;

        String query = String.format("SELECT * FROM %s WHERE nome = ?", tableName);

        Cursor cursor = getCursor(nome, db, query);
        disciplina = getObject(cursor, disciplina);

        cursor.close();

        return disciplina;
    }

    private static Cursor getCursor(String descricao, SQLiteDatabase db, String query) {
        return db.rawQuery(query, new String[]{String.valueOf(descricao)});
    }
}
