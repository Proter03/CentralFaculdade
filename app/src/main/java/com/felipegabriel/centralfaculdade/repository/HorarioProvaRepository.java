package com.felipegabriel.centralfaculdade.repository;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioProva;

public class HorarioProvaRepository extends GenericDatabase<HorarioProva>{
    public HorarioProvaRepository(Context context, Class<HorarioProva> clazz) {
        super(context, clazz);
    }
}
