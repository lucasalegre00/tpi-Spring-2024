package com.projectapp.TPISPRING.service.categoria;

import com.projectapp.TPISPRING.domain.Categoria;
import com.projectapp.TPISPRING.dto.categoria.CategoriaDto;
import com.projectapp.TPISPRING.mappers.categoria.CategoriaMappers;
import com.projectapp.TPISPRING.repository.categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository categoriaRepository;
    private CategoriaMappers categoriaMappers;

    @Override
    public Categoria getCategoriaById(Long id) {
//
//        Optional<Categoria> optionalCategoria= categoriaRepository.findById(id);
//
//        if(optionalCategoria.isPresent()){
//            return optionalCategoria.get();
//    }else {
//        throw  new NoSuchElementException("Categoria no encontrada");
//        }
        return null;
    }

    @Override
    public CategoriaDto createCategoria(CategoriaDto categoria) {

        Categoria categoriaCreated= categoriaMappers.CategoriaDtotoCategoria(categoria);
        return categoriaMappers.CategoriaToCategoriaDto(categoriaRepository.save(categoriaCreated));
    }

    @Override
    public List<CategoriaDto> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map( categoria -> categoriaMappers.CategoriaToCategoriaDto(categoria) )
                .toList();
    }


}
