package com.projectapp.TPISPRING.dto.paso;

import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;

import java.util.List;

public record ActualizarPasoDto(
        String descripcion,
        Integer tiempo,
        Boolean esOpcional,
        List<IngredientesDto> ingredientes
) {
}
