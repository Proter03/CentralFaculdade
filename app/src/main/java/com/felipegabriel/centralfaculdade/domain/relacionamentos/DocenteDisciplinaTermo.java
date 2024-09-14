package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocenteDisciplinaTermo {
    private int id;
    private int idProfessor;
    private int idMateria;
    private int idTermo;
    private int ativo;
}
