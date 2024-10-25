package com.projectapp.TPISPRING.mappers.ingredientes;

import com.projectapp.TPISPRING.domain.Ingrediente;
import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Primary
public class IngredienteMappersImpl implements  IngredienteMappers{



    @Override
    public Ingrediente ingredienteDtoToIngrediente(IngredientesDto ingredientesDto) {
        Ingrediente ingredienteCreate= new Ingrediente();
        if(ingredienteCreate != null) {
            ingredienteCreate.setId(ingredientesDto.id());
            ingredienteCreate.setNombre(ingredientesDto.nombre());
        }
        else {
            throw new NoSuchElementException("no existe el ingrediente");
        }
        return  ingredienteCreate;
    }

    @Override
    public IngredientesDto ingredienteToIngredienteDto(Ingrediente ingrediente) {
        if(ingrediente !=null){
        return new IngredientesDto(
                ingrediente.getId(),
                ingrediente.getNombre()

        );
    }else{
        throw new NoSuchElementException("no existe el ingrediente");}
}
    @Override
        public IngredientesDto toIngredienteDto(Ingrediente ingrediente) {
            return new IngredientesDto(ingrediente.getId(), ingrediente.getNombre());
        }
    @Override
        public List<IngredientesDto> toIngredienteDtoList(List<Ingrediente> ingredientes) {
            return ingredientes.stream()
                    .map(this::toIngredienteDto)
                    .collect(Collectors.toList());
        }
    }


