package com.projectapp.TPISPRING.service.paso;

import com.projectapp.TPISPRING.domain.Ingrediente;
import com.projectapp.TPISPRING.domain.Paso;
import com.projectapp.TPISPRING.domain.Receta;
import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;
import com.projectapp.TPISPRING.dto.paso.ActualizarPasoDto;
import com.projectapp.TPISPRING.dto.paso.PasoDto;
import com.projectapp.TPISPRING.mappers.paso.PasoMappers;
import com.projectapp.TPISPRING.repository.ingrediente.IngredienteRepository;
import com.projectapp.TPISPRING.repository.paso.PasoRepository;
import com.projectapp.TPISPRING.repository.receta.RecetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PasoServiceImpl implements PasoService{

    private final PasoRepository pasoRepository;
    private final RecetaRepository recetaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final PasoMappers pasoMappers;

    public PasoDto actualizarPaso(UUID recetaId, UUID pasoId, ActualizarPasoDto pasoDto) {
        // se busca la receta por el id
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + recetaId));

        // se busca el paso por su ID
        Paso paso = pasoRepository.findById(pasoId)
                .orElseThrow(() -> new RuntimeException("Paso no encontrado con ID: " + pasoId));

        // aca se verifica que el paso pertenece a la receta
        if (!paso.getReceta().getId().equals(recetaId)) {
            throw new RuntimeException("El paso no pertenece a la receta proporcionada");
        }

        // esto Actualiza solo los campos permitidos
        paso.setDescripcion(pasoDto.descripcion());
        paso.setTiempo(pasoDto.tiempo());
        paso.setEsOpcional(pasoDto.esOpcional());

        // esto actualiza la lista de ingredientes si se los menciona en el body


        if (pasoDto.ingredientes() != null) {
            List<Ingrediente> nuevosIngredientes = pasoDto.ingredientes().stream()
                    .map(ingredienteDto -> ingredienteRepository.findById(ingredienteDto.id())
                            .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado con ID: " + ingredienteDto.id())))
                    .collect(Collectors.toList());
            paso.setIngredientes(nuevosIngredientes);
        }


        pasoRepository.save(paso);
        return pasoMappers.pasoToPasoDto(paso);
    //aca en actualizarPaso me quedaria que si se agrega un ingrediente, con colocar solo el nombre me genere un id incremental
    }

}
