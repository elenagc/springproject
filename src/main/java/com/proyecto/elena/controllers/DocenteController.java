package com.proyecto.elena.controllers;

import com.proyecto.elena.dto.DocenteRequestDto;
import com.proyecto.elena.dto.DocenteResponseDto;
import com.proyecto.elena.mappers.DocenteMapper;
import com.proyecto.elena.dto.DocenteRequestDto;
import com.proyecto.elena.services.DocenteService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.Optional;
/**
 * Controlador de Docentes
 * Aquí se implementan los métodos de la API REST
 * Es un controlador REST, por lo que se le indica con la anotación @RestController
 * El path base de la API REST es /api/docentes y se le indica con la anotación @RequestMapping
 */
@RestController
@RequestMapping("/api/docentes")
@Slf4j // Para el log

public class DocenteController {

    private final DocenteService docenteService;
    private final DocenteMapper docenteMapper;

    // Inyectamos el repositorio de docentes con la anotación @Autowired
    @Autowired
    public DocenteController(DocenteService docenteService, DocenteMapper docenteMapper) {
        this.docenteService = docenteService;
        this.docenteMapper = docenteMapper;
    }

    // Aquí se implementan los métodos de la API REST

    // GET: /api/raquetas
    @GetMapping("")
    public ResponseEntity<List<DocenteResponseDto>> getAllDocentes(
    ) {
        log.info("getAlldocentes");
        return ResponseEntity.ok(
                docenteMapper.toResponse(docenteService.findAll())
        );

    }

    // GET: /api/raquetas/{id}
    // @PathVariable: Indica que el parámetro de la función es un parámetro de la URL en este caso {id}
    @GetMapping("/{id}")
    public ResponseEntity<DocenteResponseDto> getDocenteById(
            @PathVariable Long id
    ) {
        log.info("getdocenteById");
        // Existe la raqueta?
        var docente = docenteService.findById(id);
        // Si existe la devolvemos
        if (docente.isPresent()) {
            return ResponseEntity.ok(
                    docenteMapper.toResponse(docente.get())
            );
        } else {
            // Si no existe devolvemos un 404
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<DocenteResponseDto> postDocente(
            @RequestBody DocenteRequestDto raqueta
    ) {
        log.info("addDocente");
        // Añadimos la raqueta
        var raquetaSaved = docenteService.save(docenteMapper.toModel(raqueta));
        // Devolvemos created
        return ResponseEntity.created(null).body(
                docenteMapper.toResponse(raquetaSaved)
        );
    }


    // PUT: /api/raquetas/{id}
    // @PathVariable: Indica que el parámetro de la función es un parámetro de la URL en este caso {id}
    // @RequestBody: Indica que el parámetro de la función es un parámetro del cuerpo de la petición HTTP
    @PutMapping("/{id}")
    public ResponseEntity<DocenteResponseDto> putDocente(
            @PathVariable Long id,
            @RequestBody DocenteRequestDto docente
    ) {
        log.info("putdocente");
        // Existe la raqueta?
        var docenteDB = docenteService.findById(id);
        // Si existe la actualizamos
        if (docenteDB.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Actualizamos los datos
        docenteDB.get().setEspecialidad(docente.getEspecialidad());
       // DB.get().setImagen(raqueta.getImagen());
        // Guardamos los cambios
       docenteService.save(docenteDB.get());
        // Devolvemos el OK
        return ResponseEntity.ok(
                docenteMapper.toResponse(docenteDB.get())
        );
    }

    // DELETE: /api/raquetas/{id}
    // @PathVariable: Indica que el parámetro de la función es un parámetro de la URL en este caso {id}
    @DeleteMapping("/{id}")
    public ResponseEntity<DocenteResponseDto> deleteDocente(
            @PathVariable Long id
    ) {
        log.info("deleteRaqueta");
        // Existe la raqueta?
        var docenteDB = docenteService.findById(id);
        // Si existe la borramos
        if (docenteDB.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
      docenteService.deleteById(docenteDB.get().getId());
        // Devolvemos el OK o No Content
        // return ResponseEntity.ok(raquetaDB.get());
        return ResponseEntity.noContent().build();
    }


}

