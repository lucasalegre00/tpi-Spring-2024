package com.projectapp.TPISPRING.mappers.paso;

import com.projectapp.TPISPRING.domain.Paso;
import com.projectapp.TPISPRING.dto.paso.PasoDto;

public interface PasoMappers {

    Paso pasoDtoToPaso(PasoDto pasoDto);
    PasoDto pasoToPasoDto(Paso paso);
}
