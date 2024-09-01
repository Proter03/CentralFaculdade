package com.felipegabriel.centralfaculdade.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.felipegabriel.centralfaculdade.domain.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlunoDAO extends GenericDAO<Aluno> {
    private final String COLUNA_ID = "ID";
    private final String COLUNA_NOME = "NOME";
    private final String COLUNA_DATA_CADASTRO = "DATA_CADASTRO";
    private final String COLUNA_ATIVO = "ATIVO";

    public AlunoDAO(Context context){
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context, this);
        this.nomeTabela = "ALUNO";
        this.dataBase = persistenceHelper.getWritableDatabase();
    }

    @Override
    public void salvar(Aluno value) {
        ContentValues values = gerarContentValuesImoveis(value);
        dataBase.insert(this.nomeTabela, null, values);
    }

    @Override
    public Aluno findById(int id) {
        String query = String.format(Locale.ENGLISH,"SELECT * FROM %s WHERE %s = %d", this.nomeTabela, COLUNA_ID, id);
        Cursor cursor = dataBase.rawQuery(query, null);
        List<Aluno> alunos = constroiAluno(cursor);
        if (alunos.size() > 1) {
            throw new IllegalStateException("Existem mais de um aluno com esse ID");
        }
        return alunos.stream().findFirst().orElse(null);
    }

    @Override
    public List<Aluno> findAll() {
        String query = String.format(Locale.ENGLISH,"SELECT * FROM %s", this.nomeTabela);
        Cursor cursor = dataBase.rawQuery(query, null);
        return constroiAluno(cursor);
    }

    @Override
    public String scriptCreate() {
        return String.format("CREATE TABLE %s (%s %s PRIMARY KEY, %s %s, %s %s, %s %s)",
                this.nomeTabela, COLUNA_ID, "INTEGER", COLUNA_NOME, "TEXT", COLUNA_DATA_CADASTRO, "DATETIME", COLUNA_ATIVO, "INTEGER");
    }

    @Override
    public String scriptDrop() {
        return String.format("DROP TABLE IF EXISTS %s", this.nomeTabela);
    }

    private ContentValues gerarContentValuesImoveis(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, aluno.getNome());
        values.put(COLUNA_DATA_CADASTRO, aluno.getDataCadastro().toString());
        values.put(COLUNA_ATIVO, aluno.getAtivo());

        return values;
    }

    private List<Aluno> constroiAluno(Cursor cursor) {
        List<Aluno> alunos = new ArrayList<>();
        if (cursor == null || cursor.getCount() <= 0)
            return new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                for (int index = 0; index < cursor.getCount(); index++) {
                    int indexId = cursor.getColumnIndex(COLUNA_ID);
                    int indexNome = cursor.getColumnIndex(COLUNA_NOME);
                    int indexDataCadastro = cursor.getColumnIndex(COLUNA_DATA_CADASTRO);
                    int indexAtivo = cursor.getColumnIndex(COLUNA_ATIVO);

                    int id = cursor.getInt(indexId);
                    String nome = cursor.getString(indexNome);
                    LocalDate dataCadastro = LocalDate.parse(cursor.getString(indexDataCadastro));
                    int ativo = cursor.getInt(indexAtivo);
                    alunos.add(new Aluno(id, nome, dataCadastro, ativo));
                    cursor.moveToNext();
                }
            }
            return alunos;
        } catch (Exception e) {
            throw new RuntimeException("Erro construir aluno");
        }
    }
}
