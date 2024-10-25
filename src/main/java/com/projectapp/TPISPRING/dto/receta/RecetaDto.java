package com.projectapp.TPISPRING.dto.receta;

import com.projectapp.TPISPRING.domain.enums.DificultadEnum;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.dto.paso.PasoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record RecetaDto(
        UUID id,
        String nombre,
        DificultadEnum dificultad,
        String descripcion,
        CategoriaDto categoria,
        List<PasoDto> pasos
        ) {

}
