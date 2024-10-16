package com.projectapp.TPISPRING.domain;

import com.projectapp.TPISPRING.domain.enums.OpcionEnum;
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
public class Paso {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer tiempo;

    @Enumerated(EnumType.STRING)
    private OpcionEnum opcional;

    @ManyToOne
    private Receta receta;git 

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "paso_id")
    private List<Ingrediente> ingredientes;


}
