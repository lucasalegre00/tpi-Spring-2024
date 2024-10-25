package com.projectapp.TPISPRING.dto.receta;

import com.projectapp.TPISPRING.dto.paso.PasoDto;
import com.projectapp.TPISPRING.dto.paso.PasoListadoDto;

import java.util.List;
import java.util.UUID;

public record RecetaListadoDto(
        UUID uuid,
        List<PasoListadoDto> listadoPasos

) {
}
