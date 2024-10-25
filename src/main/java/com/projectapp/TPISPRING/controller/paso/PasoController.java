package com.projectapp.TPISPRING.controller.paso;

import com.projectapp.TPISPRING.dto.paso.ActualizarPasoDto;
import com.projectapp.TPISPRING.dto.paso.PasoDto;
import com.projectapp.TPISPRING.service.paso.PasoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PasoController {

        private PasoService pasoService;

        @PutMapping("/api/v1/{recetaId}/pasos/{pasoId}")
        public ResponseEntity<PasoDto> actualizarPaso(@PathVariable UUID recetaId,
                                                      @PathVariable(required = false, name = "pasoId") UUID pasoId,
                                                      @RequestBody ActualizarPasoDto pasoDto) {
            PasoDto pasoActualizado = pasoService.actualizarPaso(recetaId, pasoId, pasoDto);
            return ResponseEntity.ok(pasoActualizado);
        }

}
