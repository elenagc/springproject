package com.proyecto.elena.repositories;
import com.proyecto.elena.data.DocenteFactory;
import com.proyecto.elena.models.Docente;
import com.proyecto.elena.repositories.DocenteRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


/**
 * Implementación de la interfaz DocenteRepository para la gestión de datos
 */

// Repository: Indica que es un repositorio de datos para Spring
@Repository
// Slf4j: Nos permite usar el Logger
@Slf4j
public class DocenteRepositoryImpl implements DocenteRepository{

    // Creamos un mapa de docnete
    private final Map<Long, Docente> docentes = DocenteFactory.getDocentesDemoData();
    @Override
    public List<Docente> findAll() {
            log.info("findAll");
            // Devolvemos el mapa de raquetas
            return List.copyOf(docentes.values());
    }

    @Override
    public Optional<Docente> findById(Long aLong) {
        return Optional.ofNullable(docentes.get(aLong));
    }

    @Override
    public Boolean existsById(Long aLong) {
        return docentes.containsKey(aLong);
    }

    @Override
    public Docente save(Docente docente) {
        log.info("save");
        // Guardamos la raqueta en el mapa, si no existe la crea
        // Existe?
        if (docentes.containsKey(docente.getId())) {
            // Si existe la actualizamos
            return update(docente);

        } else {
            // Si no existe la creamos
            return create(docente);
        }
    }

    /**
     * Creamos una raqueta
     *
     * @param docente Raqueta a crear
     * @return Raqueta creada
     */
    private Docente create(Docente docente) {
        log.info("create");
        // Lo primero es obtener el último ID
        long lastId = docentes.keySet().stream().max(Long::compareTo).orElse(0L);
        // Aumentamos el ID en 1
        lastId++;
        LocalDateTime now = LocalDateTime.now();
        // Asignamos el nuevo ID a la raqueta, el UUID y actualizamos el createdAt y el update
        var newDocente = new Docente(
                lastId,
                UUID.randomUUID(),
                docente.getNombre(),
                docente.getEspecialidad(),
                now,
                now,
                false
        );
        // Guardamos la raqueta en el mapa
       docentes.put(lastId, newDocente);
        // Devolvemos la raqueta
        return newDocente;
    }

    /**
     * Actualizamos una raqueta
     *
     * @param docente Raqueta a actualizar
     * @return Raqueta actualizada
     */
    private Docente update(Docente docente) {
        log.info("update");
        var now = LocalDateTime.now();
        // Obtenemos la raqueta por su ID
        var docenteToUpdate = docentes.get(docente.getId());
        // Actualizamos los datos que pueden ser modificado
        docenteToUpdate.setEspecialidad(docente.getEspecialidad());
        docenteToUpdate.setUpdatedAt(now);
        // Guardamos la raqueta en el mapa
        docentes.put(docente.getId(), docenteToUpdate);
        // Devolvemos la raqueta
        return docenteToUpdate;
    }

    /**
     * Borramos una raqueta por su ID
     *
     * @param id ID de la raqueta
     */
    @Override
    public void deleteById(Long id) {
        log.info("deleteById");
        // Borramos la raqueta por su ID
        docentes.remove(id);
    }

    /**
     * Borramos una raqueta
     *
     * @param docente Raqueta a borrar
     */
    @Override
    public void delete(Docente docente) {
        log.info("delete");
        // Borramos la raqueta por su ID
        docentes.remove(docente.getId());
    }

    /**
     * Devolvemos el número de raquetas
     *
     * @return long con el número de raquetas
     */
    @Override
    public long count() {
        log.info("count");
        // Devolvemos el número de raquetas
        return docentes.size();
    }

    /**
     * Borramos todas las raquetas
     */
    @Override
    public void deleteAll() {
        log.info("deleteAll");
        // Borramos todas las raquetas
       docentes.clear();
    }

    /**
     * Devolvemos todas las raquetas de una marca
     *
     * @param nombre Marca de las raquetas
     * @return Iterable<Raqueta> con las raquetas de una marca
     */
    @Override
    public List<Docente> findAllByNombre(String nombre) {
        log.info("findAllByNombre");
        // Devolvemos todas las raquetas de una marca
        return docentes.values().stream()
                .filter(docente -> docente.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<Docente> findByUuid(UUID uuid) {
        return docentes.values().stream()
                .filter(docente -> docente.getUuid().equals(uuid))
                .findFirst();
    }
}
