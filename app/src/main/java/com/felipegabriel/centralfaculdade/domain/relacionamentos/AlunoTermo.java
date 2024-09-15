package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AlunoTermo {
    private int id;
    private int idAluno;
    private int idTermo;
}
