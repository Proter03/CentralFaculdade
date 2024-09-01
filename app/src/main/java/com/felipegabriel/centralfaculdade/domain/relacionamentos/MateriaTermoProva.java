package com.felipegabriel.centralfaculdade.domain.relacionamentos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MateriaTermoProva {
    private int idMateria;
    private int idTermo;
    private LocalDateTime dataProva;
    private int prioridade;
    private int ativo;
}
