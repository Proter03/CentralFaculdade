package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Grade {
    private int id;
    private int idCurso;
    private int idDisciplina;
    private int idTermo;
    private Double horas;
    private String emenda;
}
