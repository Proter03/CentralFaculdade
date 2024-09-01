package com.felipegabriel.centralfaculdade.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
    private int id;
    private String descricao;
    private int ativo;
}
