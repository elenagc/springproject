package com.proyecto.elena.dto;

import lombok.Data;
/**
 * DTO para crear un docente
 */
@Data
public class DocenteRequestDto {

        private final String nombre;
        private final String especialidad;
}

