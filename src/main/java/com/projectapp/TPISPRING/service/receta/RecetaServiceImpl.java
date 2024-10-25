package com.projectapp.TPISPRING.service.receta;

import com.projectapp.TPISPRING.domain.Ingrediente;
import com.projectapp.TPISPRING.domain.Paso;
import com.projectapp.TPISPRING.domain.Receta;
import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;
import com.projectapp.TPISPRING.dto.receta.RecetaListadoDto;
import com.projectapp.TPISPRING.mappers.ingredientes.IngredienteMappers;
import com.projectapp.TPISPRING.mappers.receta.RecetaMappers;
import com.projectapp.TPISPRING.repository.paso.PasoRepository;
import com.projectapp.TPISPRING.repository.receta.RecetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private RecetaMappers recetaMappers;
    private RecetaRepository recetaRepository;
    private PasoRepository pasoRepository;
    private IngredienteMappers ingredienteMappers;
    private List<Ingrediente> ingrediente;

    @Override
    public RecetaDto createReceta(RecetaDto receta) {

        Receta recetaCreated= recetaMappers.recetaDtoToReceta(receta);
        return recetaMappers.recetaToRecetaDto(recetaRepository.save(recetaCreated));
    }

    @Override
//    public List<RecetaDto> getAllRecetas() {
//        return recetaRepository.findAll().stream().map(receta -> recetaMappers.recetaToRecetaDto(receta)).toList();
//    }
    public List<RecetaDto> getAllRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        return recetas.stream().map(receta -> recetaMappers.recetaToRecetaDto(receta)).collect(Collectors.toList());
    }

    @Override
    public Optional<RecetaDto> closeReceta(UUID uuid) {
        Optional<Receta> receta = recetaRepository.findById(uuid);

        if ( receta.isPresent() ){
            var recetaEncontrado = receta.get();
            var recetaDto = recetaRepository.save( recetaEncontrado );
            return Optional.of(recetaMappers.recetaToRecetaDto(recetaDto)  );
        }

        return Optional.empty();
    }
    @Override
    public Optional<RecetaDto> getRecetaById(UUID uuid) {
        Optional<Receta> optionalReceta= recetaRepository.findById(uuid);

        if (optionalReceta.isPresent()) {
            return Optional.of(
                    recetaMappers.recetaToRecetaDto(optionalReceta.get())
            );
        }else {
            throw new NoSuchElementException("receta no encontrado");
        }
    }

    @Override
        public boolean eliminarReceta(UUID id) {
        if (recetaRepository.existsById(id)) {

            recetaRepository.deleteById(id);
            return true;
        }

        return false;
    }
//        Receta receta = recetaRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Receta no encontrada con ID: " + id));
//
//            recetaRepository.delete(receta);
//        }

    public List<IngredientesDto> obtenerIngredientes(UUID recetaId, UUID pasoId) {
        // Buscar la receta por su ID
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + recetaId));

        // Si se proporciona el ID del paso, buscar ingredientes solo de ese paso
        if (pasoId != null) {
            Paso paso = pasoRepository.findById(pasoId)
                    .orElseThrow(() -> new RuntimeException("Paso no encontrado con ID: " + pasoId));

            // Verificar que el paso pertenezca a la receta
            if (!paso.getReceta().getId().equals(recetaId)) {
                throw new RuntimeException("El paso no pertenece a la receta proporcionada");
            }

            return ingredienteMappers.toIngredienteDtoList(paso.getIngredientes());
        }

        // Si no se proporciona el ID del paso, devolver ingredientes de todos los pasos
        List<Ingrediente> ingredientes = receta.getPasos().stream()
                .flatMap(p -> p.getIngredientes().stream())
                .toList();

        return ingredienteMappers.toIngredienteDtoList(ingredientes);
    }

    @Override
    public List<RecetaListadoDto> obtenerRecetasPorCategoria(UUID categoriaId) {
        List<Receta> recetas = recetaRepository.findByCategoriaId(categoriaId);
        return recetaMappers.recetasToRecetaListadoDto(recetas);
    }

    }








