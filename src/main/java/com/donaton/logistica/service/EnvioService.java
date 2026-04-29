package com.donaton.logistica.service;

import com.donaton.logistica.model.Envio;
import com.donaton.logistica.repository.EnvioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {

    private final EnvioRepository repo;

    public EnvioService(EnvioRepository repo) {
        this.repo = repo;
    }

    public List<Envio> listar() {
        return repo.findAll();
    }

    public Envio guardar(Envio e) {
        return repo.save(e);
    }

    public Envio buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}