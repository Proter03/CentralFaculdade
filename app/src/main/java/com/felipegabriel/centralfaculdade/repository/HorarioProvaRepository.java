package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioProva;

import java.util.ArrayList;
import java.util.List;

public class HorarioProvaRepository extends GenericDatabase<HorarioProva> {
    private final String tableName = HorarioProva.class.getSimpleName();

    public HorarioProvaRepository(Context context, Class<HorarioProva> clazz) {
        super(context, clazz);
    }

    public List<HorarioProva> findByIdCursoAndIdTermo(int idCurso, int idTermo) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        List<HorarioProva> horariosProvas = new ArrayList<>();

        String query = String.format("SELECT * FROM %s WHERE idCurso = ? AND idTermo = ? ", tableName);

        List<Integer> parametros = new ArrayList<>();
        parametros.add(idCurso);
        parametros.add(idTermo);
        Cursor cursor = getCursor(parametros, db, query);

        horariosProvas = getObject(cursor, horariosProvas);

        cursor.close();
        return horariosProvas;
    }
}
