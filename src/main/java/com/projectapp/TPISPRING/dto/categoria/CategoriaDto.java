package com.projectapp.TPISPRING.dto.categoria;

import java.util.UUID;

public record CategoriaDto(
        UUID id,
        String nombre
) {
}
