package com.proyecto.elena.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * DTO para devolver un docente
 */

@Data
public class DocenteResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String nombre;
    private final String especialidad;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Boolean deleted;
}
