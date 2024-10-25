package com.projectapp.TPISPRING.repository.paso;

import com.projectapp.TPISPRING.domain.Paso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasoRepository extends JpaRepository<Paso, UUID>{
}
