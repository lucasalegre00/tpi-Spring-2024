package com.projectapp.TPISPRING.mappers.categoria;

import com.projectapp.TPISPRING.domain.Categoria;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.dto.receta.RecetaDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoriaMappersImpl implements CategoriaMappers{
    // Convierte una entidad Categoria en un DTO CategoriaDTO

    @Override
    public Categoria CategoriaDtotoCategoria(CategoriaDto categoriaDto) {
        Categoria categoriaCreate = new Categoria();
//        categoriaCreate.setId(categoriaDto.id());
        categoriaCreate.setId(UUID.randomUUID());
        categoriaCreate.setNombre(categoriaDto.nombre());
        return categoriaCreate;
    }
    // Convierte un DTO CategoriaDTO en una entidad Categoria
    @Override
    public CategoriaDto CategoriaToCategoriaDto(Categoria categoria) {
        CategoriaDto categoriaDto = new CategoriaDto(
                categoria.getId(),
                categoria.getNombre()
        );
        return categoriaDto;
    }


}
