package com.felipegabriel.centralfaculdade.service;

import android.content.Context;

import com.felipegabriel.centralfaculdade.domain.Termo;
import com.felipegabriel.centralfaculdade.repository.TermoRepository;

import lombok.NonNull;

public class TermoService {
    private final TermoRepository termoRepository;

    public TermoService(Context context) {
        this.termoRepository = new TermoRepository(context, Termo.class);
    }

    public Termo getTermoById(int idTermo) {
        return getById(idTermo);
    }

    public long getCount() {
        return termoRepository.countAll();
    }

    public void criaTermo(String descricao) {
        montaTermo(descricao);
    }

    private void montaTermo(String descricao) {
        Termo termo = instanciaGrade(descricao);

        setaIdTermo(termo, (int) salvar(termo));
    }

    private Termo getById(int idTermo) {
        return termoRepository.findById(idTermo).orElseThrow(() -> new RuntimeException("Nenhum Termo com esse ID foi encontrado.: " + idTermo));
    }
    @NonNull
    private static Termo instanciaGrade(String descricao) {
        Termo termo = new Termo();
        termo.setDescricao(descricao);

        return termo;
    }
    private void setaIdTermo(Termo termo, int id) {
        termo.setId(id);
    }
    private long salvar(Termo termo) {
        return termoRepository.save(termo);
    }
}
