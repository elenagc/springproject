package com.proyecto.elena.models;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
/**
 * Clase Docente POJO
 */

// Data: Genera los getters y setters, toString, equals, hashCode y el constructor con todos los parámetros necesarios (finals)
@Data
// AllArgsConstructor: Genera el constructor con todos los parámetros
@AllArgsConstructor
public class Docente {
    private final Long id;
    private final UUID uuid;
    private final String nombre;
    private String especialidad;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;


}
