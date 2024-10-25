package com.projectapp.TPISPRING.repository.ingrediente;

import com.projectapp.TPISPRING.domain.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
