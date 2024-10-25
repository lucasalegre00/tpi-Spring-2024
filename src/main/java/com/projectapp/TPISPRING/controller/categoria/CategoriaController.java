package com.projectapp.TPISPRING.controller.categoria;


import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.dto.receta.RecetaListadoDto;
import com.projectapp.TPISPRING.service.categoria.CategoriaService;
import com.projectapp.TPISPRING.service.receta.RecetaService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;
    private RecetaService recetaService;

    @PostMapping("/api/v1/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody CategoriaDto categoria) {

        CategoriaDto categoriaDto = categoriaService.createCategoria(categoria);

        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(categoriaDto);
    }

    @GetMapping("/api/v1/categorias")
    public List<CategoriaDto> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/api/v1/categoria/{categoriaId}")
    public List<RecetaListadoDto> obtenerRecetasPorCategoria(@PathVariable UUID categoriaId) {
        return recetaService.obtenerRecetasPorCategoria(categoriaId);
    }

}
