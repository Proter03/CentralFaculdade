package com.felipegabriel.centralfaculdade.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
    private int id;
    private String nome;
    private LocalDate dataCadastro;
    private int ativo;
}
