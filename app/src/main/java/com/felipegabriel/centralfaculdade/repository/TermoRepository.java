package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Termo;

public class TermoRepository extends GenericDatabase<Termo>{
    private final String tableName = Termo.class.getSimpleName();
    public TermoRepository(Context context, Class<Termo> clazz) {
        super(context, clazz);
    }
}
