package com.projectapp.TPISPRING.service.receta;

import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;
import com.projectapp.TPISPRING.dto.receta.RecetaListadoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecetaService {

    RecetaDto createReceta(RecetaDto receta);

    List<RecetaDto> getAllRecetas();

    Optional<RecetaDto> closeReceta(UUID uuid);

    Optional<RecetaDto> getRecetaById(UUID uuid);

    boolean eliminarReceta(UUID id);

    List<IngredientesDto> obtenerIngredientes(UUID recetaId, UUID pasoId);

    List<RecetaListadoDto> obtenerRecetasPorCategoria(UUID categoriaId);
}
