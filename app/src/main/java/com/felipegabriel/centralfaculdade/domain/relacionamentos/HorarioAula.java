package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HorarioAula {
    private int id;
    private int idDiaDaSemana;
    private LocalTime horario;
    private int idDisciplina;
    private int idTermo;
}
