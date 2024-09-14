package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoDisciplinaTermo {
    private int id;
    private int idCurso;
    private int idMateria;
    private int idTermo;
    private Float horas;
    private String Emenda;
}
