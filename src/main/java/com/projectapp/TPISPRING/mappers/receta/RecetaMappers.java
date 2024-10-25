package com.projectapp.TPISPRING.mappers.receta;

import com.projectapp.TPISPRING.domain.Receta;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;
import com.projectapp.TPISPRING.dto.receta.RecetaListadoDto;

import java.util.List;

public interface RecetaMappers {

    Receta recetaDtoToReceta(RecetaDto recetaDto);
    RecetaDto recetaToRecetaDto(Receta receta);


    RecetaListadoDto recetaToRecetaListadoDto(Receta receta);

    // Método para convertir una lista de Receta a una lista de RecetaDto
    List<RecetaListadoDto> recetasToRecetaListadoDto(List<Receta> recetas);

    // Método para calcular el tiempo total
    long calcularTiempoTotal(Receta receta);
}
