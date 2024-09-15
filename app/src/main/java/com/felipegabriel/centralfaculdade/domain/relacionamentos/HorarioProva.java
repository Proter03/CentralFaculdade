package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HorarioProva {
    private int id;
    private int idDisciplina;
    private LocalDateTime dataProva;
    private int idTermo;
    private int idCurso;
}
