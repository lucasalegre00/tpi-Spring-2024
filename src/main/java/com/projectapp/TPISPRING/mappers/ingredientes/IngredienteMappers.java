package com.projectapp.TPISPRING.mappers.ingredientes;

import com.projectapp.TPISPRING.domain.Ingrediente;
import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;

import java.util.List;

public interface IngredienteMappers {

    Ingrediente ingredienteDtoToIngrediente(IngredientesDto ingredientesDto);
    IngredientesDto ingredienteToIngredienteDto(Ingrediente ingrediente);

    IngredientesDto toIngredienteDto(Ingrediente ingrediente);

    List<IngredientesDto> toIngredienteDtoList(List<Ingrediente> ingredientes);
}
