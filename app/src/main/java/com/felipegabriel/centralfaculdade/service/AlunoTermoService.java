package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.relacionamentos.AlunoTermo;
import com.felipegabriel.centralfaculdade.repository.AlunoTermoRepository;

public class AlunoTermoService {
    private final AlunoTermoRepository alunoTermoRepository;

    public AlunoTermoService(Context context) {
        this.alunoTermoRepository = new AlunoTermoRepository(context, AlunoTermo.class);
    }

    public void criaAlunoTermo(AlunoTermo alunoTermo) {
        alunoTermoRepository.save(alunoTermo);
    }

    public long countAll() {
        return alunoTermoRepository.countAll();
    }
}
