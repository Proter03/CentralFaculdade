package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.Aluno;

public class AlunoRepository extends GenericDatabase<Aluno> {
    public AlunoRepository(Context context, Class<Aluno> clazz) {
        super(context, clazz);
    }

    public Aluno findByIdUser(int idUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        Aluno aluno = null;

        String tableName = Aluno.class.getSimpleName();
        String query = String.format("SELECT * FROM %s WHERE idUsuario = ?", tableName);
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idUser)});

        aluno = getObject(cursor, aluno);

        cursor.close();
        return aluno;
    }
}
