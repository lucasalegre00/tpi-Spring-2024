package com.projectapp.TPISPRING.mappers.paso;

import com.projectapp.TPISPRING.domain.Categoria;
import com.projectapp.TPISPRING.domain.Paso;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;
import com.projectapp.TPISPRING.dto.paso.PasoDto;
import com.projectapp.TPISPRING.mappers.ingredientes.IngredienteMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PasoMappersImpl implements PasoMappers{

    private IngredientesDto ingredientesDto;
    @Autowired
    private IngredienteMappers ingredienteMappers;

    @Override
    public Paso pasoDtoToPaso(PasoDto pasoDto) {

        Paso pasoCreate = new Paso();
        pasoCreate.setId(UUID.randomUUID());
        pasoCreate.setDescripcion(pasoDto.descripcion());
        pasoCreate.setTiempo(pasoDto.tiempo());
        pasoCreate.setEsOpcional(pasoDto.esOpcional());
        //pasoDto.ingredientes().stream().map(ingredienteMappers::ingredienteDtoToIngrediente).toList();
//        pasoCreate.setIngredientes(pasoDto.ingredientes().stream().map(ingredienteMappers::ingredienteDtoToIngrediente).collect(Collectors.toList()));
        pasoCreate.setIngredientes(Optional.ofNullable(pasoDto.ingredientes())
                .orElse(Collections.emptyList())
                .stream()
                .map(ingredienteMappers::ingredienteDtoToIngrediente)
                .collect(Collectors.toList()));
        return pasoCreate;
    }

    @Override
    public PasoDto pasoToPasoDto(Paso paso) {
        PasoDto pasoDto = new PasoDto(
                paso.getId(),
                paso.getDescripcion(),
                paso.getTiempo(),
                paso.getEsOpcional(),
                //paso.ingrediente().stream().map(ingredienteMapper::toDTO).toList()
                paso.getIngredientes().stream().map(ingredienteMappers::ingredienteToIngredienteDto).toList()
        );
        return pasoDto;
    }



}
