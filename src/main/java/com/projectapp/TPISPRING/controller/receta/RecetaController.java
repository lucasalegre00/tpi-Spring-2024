package com.projectapp.TPISPRING.controller.receta;

import com.projectapp.TPISPRING.domain.Receta;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.dto.errors.ErrorDto;
import com.projectapp.TPISPRING.dto.ingredientes.IngredientesDto;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;
import com.projectapp.TPISPRING.service.receta.RecetaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class RecetaController {

    private RecetaService recetaService;


    @PostMapping("/api/v1/receta")
    public ResponseEntity<?> createReceta(@RequestBody RecetaDto receta) {
        //crear la receta, la logica
        RecetaDto recetaDto = recetaService.createReceta(receta);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(recetaDto);
    }

    @GetMapping("/api/v1/receta")
    public List<RecetaDto> getAllRecetas() {
        return recetaService.getAllRecetas();
    }


    //    @PutMapping("/api/v1/receta/close/{idReceta}")
//    public ResponseEntity closeReceta(@PathVariable("idReceta") UUID idReceta){
//        Optional<RecetaDto> recetaDto = recetaService.closeReceta( idReceta );
//
//        if ( recetaDto.isEmpty() ){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado");
//        }
//
//        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
//    }
    @GetMapping("/api/v1/receta/{idReceta}")
    public ResponseEntity<?> getRecetaById(@PathVariable("idReceta") UUID idReceta) {

        Optional<RecetaDto> recetaDto = recetaService.getRecetaById(idReceta);

        if (recetaDto.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDto(
                            String.format("El proyecto(id=%s) no existe", idReceta.toString()),
                            HttpStatus.NOT_FOUND.value(),
                            "/api/v1/proyecto/" + idReceta.toString(
                            )));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recetaDto.get());
    }

    @DeleteMapping("/api/v1/receta/eliminar/{id}")
    public ResponseEntity<?> eliminarReceta(@PathVariable UUID id) {

//        recetaService.eliminarReceta(id);
//        return ResponseEntity.noContent().build();
        boolean isRecetaDeleted = recetaService.eliminarReceta( id );

        if ( isRecetaDeleted ){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @GetMapping("/api/v1/receta/{recetaId}/ingredientes")
    public ResponseEntity<List<IngredientesDto>> obtenerIngredientesPorReceta(
            @PathVariable UUID recetaId,
            @RequestParam(value = "pasoId", required = false) UUID pasoId) {

        List<IngredientesDto> ingredientes = recetaService.obtenerIngredientes(recetaId, pasoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientes);
    }

}
