package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDisciplinaNota {
    private int id;
    private int idAluno;
    private int idDisciplina;
    private int idCurso;
    private double mediaFinal;
}
