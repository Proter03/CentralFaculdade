package com.felipegabriel.centralfaculdade.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HorarioProvaDTO {
    private String nomeDisciplina;
    private int idDisciplina;
    private LocalDateTime dataProva;
    private int idTermo;
    private String descricaoTermo;
}
