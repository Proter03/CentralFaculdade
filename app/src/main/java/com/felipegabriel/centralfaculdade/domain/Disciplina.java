package com.felipegabriel.centralfaculdade.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina {
    private int id;
    private String nome;
    private String emenda;
    private Double horas;
}
