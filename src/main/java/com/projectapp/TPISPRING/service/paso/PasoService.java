package com.projectapp.TPISPRING.service.paso;


import com.projectapp.TPISPRING.dto.paso.ActualizarPasoDto;
import com.projectapp.TPISPRING.dto.paso.PasoDto;

import java.util.UUID;

public interface PasoService {
    PasoDto actualizarPaso(UUID recetaId, UUID pasoId, ActualizarPasoDto pasoDto);
}
