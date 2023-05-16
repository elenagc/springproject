package com.proyecto.elena.mappers;
import com.proyecto.elena.dto.DocenteResponseDto;
import com.proyecto.elena.dto.DocenteRequestDto;
import com.proyecto.elena.models.Docente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DocenteMapper {

    //Métodos usados para mapear los DTOS a los modelos y viceversa
    // Mapeamos de modelo a DTO
    public DocenteResponseDto toResponse(Docente docente) {
        return new DocenteResponseDto(
                docente.getId(),
                docente.getUuid(),
                docente.getNombre(),
                docente.getEspecialidad(),
                docente.getCreatedAt(),
                docente.getUpdatedAt(),
                docente.getDeleted()
        );
    }

    // Mapeamos de DTO a modelo
    public List<DocenteResponseDto> toResponse(List<Docente> docente) {
        return docente.stream()
                .map(this::toResponse)
                .toList();
    }

    // Mapeamos de DTO a modelo
    public Docente toModel(DocenteRequestDto dto) {
        return new Docente(
                0L,
                UUID.randomUUID(),
                dto.getNombre(),
                dto.getEspecialidad(),

                LocalDateTime.now(),
                LocalDateTime.now(),
                false
        );
    }

}

