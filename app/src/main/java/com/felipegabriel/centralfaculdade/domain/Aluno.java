package com.felipegabriel.centralfaculdade.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno {
    private int id;
    private int idUsuario;
    private String nome;
    private LocalDate dataCadastro = LocalDate.now();
    private int ativo = 1;
}
