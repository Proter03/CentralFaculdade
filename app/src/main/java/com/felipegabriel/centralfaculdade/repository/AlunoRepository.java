package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.Aluno;

import java.util.Optional;

public class AlunoRepository extends GenericDatabase<Aluno> {
    private final String tableName = Aluno.class.getSimpleName();
    public AlunoRepository(Context context, Class<Aluno> clazz) {
        super(context, clazz);
    }

    public Optional<Aluno> findByIdUser(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        Aluno aluno = null;

        String query = String.format("SELECT * FROM %s WHERE idUsuario = ?", tableName);
        Cursor cursor = getCursor(idUser, db, query);

        aluno = getObject(cursor, aluno);

        cursor.close();
        return Optional.ofNullable(aluno);
    }

    private static Cursor getCursor(int idUser, SQLiteDatabase db, String query) {
        return db.rawQuery(query, new String[]{String.valueOf(idUser)});
    }
}
