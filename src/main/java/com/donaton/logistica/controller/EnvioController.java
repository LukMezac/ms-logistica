package com.donaton.logistica.controller;

import com.donaton.logistica.model.Envio;
import com.donaton.logistica.service.EnvioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
@CrossOrigin("*")
public class EnvioController {

    private final EnvioService service;

    public EnvioController(EnvioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Envio> listar() {
        return service.listar();
    }

    @PostMapping
    public Envio crear(@RequestBody Envio e) {
        e.setEstado("Pendiente"); // 🔥 clave
        return service.guardar(e);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio e) {
        Envio actual = service.buscarPorId(id);

        if (actual == null) return null;

        actual.setDestino(e.getDestino());
        actual.setTransportista(e.getTransportista());
        actual.setEstado(e.getEstado());

        return service.guardar(actual);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}