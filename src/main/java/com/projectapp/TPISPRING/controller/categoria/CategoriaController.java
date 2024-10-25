package com.projectapp.TPISPRING.controller.categoria;


import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.service.categoria.CategoriaService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;

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

}
