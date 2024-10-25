package com.projectapp.TPISPRING.mappers.categoria;

import com.projectapp.TPISPRING.domain.Categoria;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;

public interface CategoriaMappers {

    Categoria CategoriaDtotoCategoria(CategoriaDto categoriaDto);
    CategoriaDto CategoriaToCategoriaDto(Categoria categoria);
}
