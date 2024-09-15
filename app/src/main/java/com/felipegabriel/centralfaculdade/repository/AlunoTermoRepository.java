package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoTermo;

public class AlunoTermoRepository extends GenericDatabase<AlunoTermo>{
    public AlunoTermoRepository(Context context, Class<AlunoTermo> clazz) {
        super(context, clazz);
    }
}
