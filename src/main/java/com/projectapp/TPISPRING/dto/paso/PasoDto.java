package com.projectapp.TPISPRING.dto.paso;

import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;

import java.util.List;
import java.util.UUID;

public record PasoDto(
        UUID id,
        String descripcion,
        Integer tiempo,
        Boolean esOpcional,
        List<IngredientesDto> ingredientes

) {
}
