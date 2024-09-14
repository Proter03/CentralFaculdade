package com.felipegabriel.centralfaculdade.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Docente {
    private int id;
    private int idUsuario;
    private String nome;
    private LocalDate dataCadastro;
    private int ativo;
}
