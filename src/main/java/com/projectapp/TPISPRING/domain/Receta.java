package com.projectapp.TPISPRING.domain;

import com.projectapp.TPISPRING.domain.enums.DificultadEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private DificultadEnum dificultad;

    @Column(length = 5000, nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Paso> pasos;

    @ManyToOne
    private Categoria categoria;


}
