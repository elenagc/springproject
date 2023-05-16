package com.proyecto.elena.repositories;
import com.proyecto.elena.models.Docente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocenteRepository extends CrudRepository<Docente, Long> {
    // Aquí puedes añadir métodos propios de la clase Docente
    List<Docente> findAllByNombre(String nombre);

    Optional<Docente> findByUuid(UUID uuid);
}

