package com.proyecto.elena.data;
import com.proyecto.elena.models.Docente;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class DocenteFactory {

    public static Map<Long, Docente> getDocentesDemoData() {
        log.info("Cargando datos de docentes");
        var map = new LinkedHashMap<Long, Docente>();
        map.put(1L,
                new Docente(1L, UUID.randomUUID(), "Eva", "DAM",  LocalDateTime.now(), LocalDateTime.now(), false));
        map.put(2L,
                new Docente(2L, UUID.randomUUID(), "Kamel", "DAW",  LocalDateTime.now(), LocalDateTime.now(), false));

        map.put(3L,
                new Docente(3L, UUID.randomUUID(), "Miguel", "SMR", LocalDateTime.now(), LocalDateTime.now(), false));


        return map;
    }
}

