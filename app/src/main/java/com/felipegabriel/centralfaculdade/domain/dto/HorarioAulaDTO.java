package com.felipegabriel.centralfaculdade.domain.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HorarioAulaDTO {
    private String diaDaSemana;
    private LocalTime horario;
    private int idDisciplina;
    private String nomeDisciplina;
}
