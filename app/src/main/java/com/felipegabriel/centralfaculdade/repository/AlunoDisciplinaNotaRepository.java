package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoDisciplinaNota;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDisciplinaNotaRepository extends GenericDatabase<AlunoDisciplinaNota>{
    private final String tableName = AlunoDisciplinaNota.class.getSimpleName();
    public AlunoDisciplinaNotaRepository(Context context, Class<AlunoDisciplinaNota> clazz) {
        super(context, clazz);
    }

    public Optional<AlunoDisciplinaNota> findByIdAlunoAndIdDisciplina(int idAluno, int idDisciplina, int idCurso) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        AlunoDisciplinaNota alunoDisciplinaNota = null;

        String query = String.format("SELECT * FROM %s WHERE idAluno = ? AND idDisciplina = ? AND idCurso = ?", tableName);
        List<Integer> parametros = new ArrayList<>();
        parametros.add(idAluno);
        parametros.add(idDisciplina);
        parametros.add(idCurso);
        Cursor cursor = getCursor(parametros, db, query);

        alunoDisciplinaNota = getObject(cursor, alunoDisciplinaNota);

        cursor.close();
        return Optional.ofNullable(alunoDisciplinaNota);
    }

    public List<AlunoDisciplinaNota> findByIdAluno(int idAluno) {
        SQLiteDatabase db = this.getWritableDatabase();
        createTableIfNotExists(db);

        List<AlunoDisciplinaNota> alunoDisciplinaNotas = new ArrayList<>();

        String query = String.format("SELECT * FROM %s WHERE idAluno = ?", tableName);

        List<Integer> parametros = new ArrayList<>();
        parametros.add(idAluno);
        Cursor cursor = getCursor(parametros, db, query);

        alunoDisciplinaNotas = getObject(cursor, alunoDisciplinaNotas);

        cursor.close();
        return alunoDisciplinaNotas;
    }
}
