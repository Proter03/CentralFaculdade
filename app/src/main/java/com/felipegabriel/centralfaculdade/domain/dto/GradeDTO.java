package com.felipegabriel.centralfaculdade.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GradeDTO {
    private int idGrade;
    private int idCurso;
    private String nomeCurso;
    private int idDisciplina;
    private String nomeDisciplina;
    private int idTermo;
    private String descricaoTermo;
    private Double horas;
    private String emenda;
}
