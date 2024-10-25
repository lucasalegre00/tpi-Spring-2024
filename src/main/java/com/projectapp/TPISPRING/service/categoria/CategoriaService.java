package com.projectapp.TPISPRING.service.categoria;

import com.projectapp.TPISPRING.domain.Categoria;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaService {

    Categoria getCategoriaById(Long id);

    CategoriaDto createCategoria(CategoriaDto categoria);

//    List<CategoriaDto> getAllCategorias(UUID Id, String nombre);

    List<CategoriaDto> getAllCategorias();
}
