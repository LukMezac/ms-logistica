package com.donaton.logistica.controller;

import com.donaton.logistica.model.Envio;
import com.donaton.logistica.service.EnvioService;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscarPorId(@PathVariable Long id) {
        Envio envio = service.buscarPorId(id);
        return (envio != null) ? ResponseEntity.ok(envio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Envio crear(@RequestBody Envio e) {
        e.setEstado("Pendiente");
        return service.guardar(e);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @RequestBody Envio e) {
        Envio actual = service.buscarPorId(id);

        if (actual == null) {
            return ResponseEntity.notFound().build();
        }

        actual.setDestino(e.getDestino());
        actual.setTransportista(e.getTransportista());
        actual.setEstado(e.getEstado());

        return ResponseEntity.ok(service.guardar(actual));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}