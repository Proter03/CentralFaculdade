package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.HorarioProva;
import com.felipegabriel.centralfaculdade.repository.HorarioProvaRepository;

public class HorarioProvaService {
    private final HorarioProvaRepository horarioProvaRepository;

    public HorarioProvaService(Context context) {
        this.horarioProvaRepository = new HorarioProvaRepository(context, HorarioProva.class);
    }
}
