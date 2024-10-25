package com.projectapp.TPISPRING.mappers.receta;

import com.projectapp.TPISPRING.domain.Receta;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;

public interface RecetaMappers {

    Receta recetaDtoToReceta(RecetaDto recetaDto);
    RecetaDto recetaToRecetaDto(Receta receta);


}
