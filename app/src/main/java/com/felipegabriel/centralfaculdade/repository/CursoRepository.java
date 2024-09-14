package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.Curso;

public class CursoRepository extends GenericDatabase<Curso> {
    private final String tableName = Curso.class.getSimpleName();
    public CursoRepository(Context context, Class<Curso> clazz) {
        super(context, clazz);
    }

    public Curso findByDescricao(String descricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        Curso curso = null;

        String query = String.format("SELECT * FROM %s WHERE descricao = ?", tableName);

        Cursor cursor = getCursor(descricao, db, query);
        curso = getObject(cursor, curso);

        cursor.close();

        return curso;
    }

    private static Cursor getCursor(String descricao, SQLiteDatabase db, String query) {
        return db.rawQuery(query, new String[]{String.valueOf(descricao)});
    }
}
