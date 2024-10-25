package com.projectapp.TPISPRING.mappers.receta;

import com.projectapp.TPISPRING.domain.Categoria;
import com.projectapp.TPISPRING.domain.Ingrediente;
import com.projectapp.TPISPRING.domain.Paso;
import com.projectapp.TPISPRING.domain.Receta;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;
import com.projectapp.TPISPRING.dto.receta.RecetaListadoDto;
import com.projectapp.TPISPRING.mappers.categoria.CategoriaMappers;
import com.projectapp.TPISPRING.mappers.ingredientes.IngredienteMappers;
import com.projectapp.TPISPRING.mappers.paso.PasoMappers;
import com.projectapp.TPISPRING.repository.categoria.CategoriaRepository;
import com.projectapp.TPISPRING.repository.paso.PasoRepository;
import com.projectapp.TPISPRING.repository.receta.RecetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecetaMappersImpl implements RecetaMappers {

    private CategoriaMappers categoriaMappers;
    private PasoMappers pasoMappers;
    private CategoriaRepository categoriaRepository;
    private PasoRepository pasoRepository;
    private IngredienteMappers ingredienteMappers;
    private RecetaRepository recetaRepository;
    private List<Ingrediente> ingrediente;

    @Override
    public Receta recetaDtoToReceta(RecetaDto recetaDto) {

        Receta recetaCreate = new Receta();
        recetaCreate.setId(UUID.randomUUID());
        recetaCreate.setNombre(recetaDto.nombre());
        recetaCreate.setDificultad(recetaDto.dificultad());
        recetaCreate.setDescripcion(recetaDto.descripcion());

        Categoria categoria = categoriaRepository.findById(recetaDto.categoria().id())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + recetaDto.categoria().id()));
        recetaCreate.setCategoria(categoria);

        List<Paso> pasos = Optional.ofNullable(recetaDto.pasos())
                .orElse(Collections.emptyList())
                .stream()
                .map(pasoDto -> {
                    Paso paso = pasoMappers.pasoDtoToPaso(pasoDto); // Convierte DTO a entidad Paso
                    paso.setReceta(recetaCreate); // Asocia el Paso a la Receta

                    // Manejo de ingredientes dentro de cada paso
                    List<Ingrediente> ingredientes = Optional.ofNullable(pasoDto.ingredientes())
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(ingredienteDto -> {
                                Ingrediente ingrediente = new Ingrediente();
                                ingrediente.setNombre(ingredienteDto.nombre());
                                ingrediente.setPaso(paso); // Asocia el Ingrediente al Paso
                                return ingrediente;
                            })
                            .collect(Collectors.toList());

                    paso.setIngredientes(ingredientes); // Establece la lista de ingredientes en el Paso
                    return paso;
                })
                .collect(Collectors.toList());

        recetaCreate.setPasos(pasos);

        return recetaCreate;

    }

    @Override
    public RecetaDto recetaToRecetaDto(Receta receta) {
        RecetaDto recetaDto = new RecetaDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDificultad(),
                receta.getDescripcion(),
                categoriaMappers.CategoriaToCategoriaDto(receta.getCategoria()),
                receta.getPasos().stream().map(pasoMappers::pasoToPasoDto).toList()

        );
        return recetaDto;

        //o se puede hacer return new directamente
    }


    @Override
    public RecetaListadoDto recetaToRecetaListadoDto(Receta receta) {
        if (receta == null) {
            return null;
        }
        long tiempoTotal = calcularTiempoTotal(receta);

        RecetaListadoDto listadoDto = new RecetaListadoDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDificultad(),
                receta.getDescripcion(),
                tiempoTotal
        );

        return listadoDto;
    }

    // Método para convertir una lista de Receta a una lista de RecetaDto
    @Override
    public List<RecetaListadoDto> recetasToRecetaListadoDto(List<Receta> recetas) {
        return recetas.stream()
                .map(this::recetaToRecetaListadoDto)
                .collect(Collectors.toList());
    }

    // Método para calcular el tiempo total
    @Override
    public long calcularTiempoTotal(Receta receta) {
        return receta.getPasos().stream()
                .filter(paso -> !paso.getEsOpcional()) // Filtrar pasos no opcionales
                .mapToLong(Paso::getTiempo) // Sumar los tiempos
                .sum();
    }




}
