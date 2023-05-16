package com.proyecto.elena.services;

import com.proyecto.elena.models.Docente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocenteService {
    List<Docente> findAll();

    Optional<Docente> findById(Long id);

    List<Docente> findAllByNombre(String nombre);

    Optional<Docente> findByUuid(UUID uuid);

   Docente save(Docente docente);

    void deleteById(Long id);

}
