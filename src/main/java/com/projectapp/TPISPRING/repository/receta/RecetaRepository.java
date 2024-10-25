package com.projectapp.TPISPRING.repository.receta;

import com.projectapp.TPISPRING.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecetaRepository extends JpaRepository<Receta, UUID> {
}
