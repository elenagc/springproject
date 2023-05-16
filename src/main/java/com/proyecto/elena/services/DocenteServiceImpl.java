package com.proyecto.elena.services;

import  com.proyecto.elena.models.Docente;
import   com.proyecto.elena.repositories.DocenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@CacheConfig(cacheNames = {"docente"}) // Nombre del caché
public class DocenteServiceImpl implements DocenteService{
    // Mis repositorios
    private final DocenteRepository docenteRepository;

    // Inyectamos los repositorios
    @Autowired
    public DocenteServiceImpl(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Override
    @Cacheable // Indicamos que se cachee, no es recomendable si hay muchos!!
    public List<Docente> findAll() {
        log.info("findAll");
        return docenteRepository.findAll();
    }

    @Override
    @Cacheable // Indicamos que se cachee
    public Optional<Docente> findById(Long id) {
        log.info("findById");
        return docenteRepository.findById(id);
    }

    @Override
    public List<Docente> findAllByNombre(String nombre) {
        log.info("findAllBynombre");
        return docenteRepository.findAllByNombre(nombre);
    }

    @Override
    @Cacheable
    public Optional<Docente> findByUuid(UUID uuid) {
        log.info("findByUuid");
        return docenteRepository.findByUuid(uuid);
    }

    @Override
    @CachePut // Indicamos que se actualice el caché
    public Docente save(Docente docente) {
        log.info("save");
        return docenteRepository.save(docente);
    }

    @Override
    @CacheEvict // Indicamos que se elimine del caché
    public void deleteById(Long id) {
        log.info("deleteById");
      docenteRepository.deleteById(id);
    }

}
