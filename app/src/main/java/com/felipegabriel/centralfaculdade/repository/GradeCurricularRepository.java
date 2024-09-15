package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.Grade;
import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioAula;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeCurricularRepository extends GenericDatabase<Grade> {
    private final String tableName = Grade.class.getSimpleName();
    public GradeCurricularRepository(Context context, Class<Grade> clazz) {
        super(context, clazz);
    }

    public Optional<Grade> findByIdCurso(int idCurso) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        String query = String.format("SELECT * FROM %s WHERE idCurso = ?", tableName);
        List<Integer> parametros = new ArrayList<>();
        parametros.add(idCurso);

        Cursor cursor = getCursor(parametros, db, query);

        Grade grade = null;
        grade = getObject(cursor, grade);

        cursor.close();
        return Optional.ofNullable(grade);
    }

    public List<Grade> findAllByIdCurso(int idCurso) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        List<Grade> grades = new ArrayList<>();

        String query = String.format("SELECT * FROM %s WHERE idCurso = ?", tableName);

        List<Integer> parametros = new ArrayList<>();
        parametros.add(idCurso);
        Cursor cursor = getCursor(parametros, db, query);

        grades = getObject(cursor, grades);

        cursor.close();
        return grades;
    }

    public Optional<Grade> findByIdCursoAndIdDisciplinaAndIdTermo(int idCurso, int idDisciplina, int idTermo) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        Grade grade = null;

        String query = String.format("SELECT * FROM %s WHERE idCurso = ? AND idDisciplina = ? AND idTermo = ?", tableName);

        List<Integer> parametros = new ArrayList<>();
        parametros.add(idCurso);
        parametros.add(idDisciplina);
        parametros.add(idTermo);
        Cursor cursor = getCursor(parametros, db, query);

        grade = getObject(cursor, grade);

        cursor.close();
        return Optional.ofNullable(grade);
    }
}
