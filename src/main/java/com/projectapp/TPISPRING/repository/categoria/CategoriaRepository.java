package com.projectapp.TPISPRING.repository.categoria;

import com.projectapp.TPISPRING.domain.Categoria;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {


}
