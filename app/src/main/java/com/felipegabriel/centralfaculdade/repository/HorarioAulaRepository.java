package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioAula;

import java.util.ArrayList;
import java.util.List;

public class HorarioAulaRepository extends GenericDatabase<HorarioAula>{
    private final String tableName = HorarioAula.class.getSimpleName();
    public HorarioAulaRepository(Context context, Class<HorarioAula> clazz) {
        super(context, clazz);
    }

    public List<HorarioAula> findByIdTermo(int idTermo, int idCurso) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        List<HorarioAula> horariosAulas = new ArrayList<>();

        String query = String.format("SELECT * FROM %s WHERE idTermo = ? AND idCurso = ?", tableName);

        List<Integer> parametros = new ArrayList<>();
        parametros.add(idTermo);
        parametros.add(idCurso);
        Cursor cursor = getCursor(parametros, db, query);

        horariosAulas = getObject(cursor, horariosAulas);

        cursor.close();
        return horariosAulas;
    }
}
