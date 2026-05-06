package com.donaton.logistica.controller;

import com.donaton.logistica.model.Envio;
import com.donaton.logistica.service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnvioController.class)
class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EnvioService envioService;

    @Test
    void listarDebeRetornar200() throws Exception {
        when(envioService.listar()).thenReturn(List.of(crearEnvio("Bogota", "DHL", "Pendiente")));

        mockMvc.perform(get("/envios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].destino").value("Bogota"));
    }

    @Test
    void buscarPorIdDebeRetornar200CuandoExiste() throws Exception {
        // CORRECCIÓN: Asegura que el servicio devuelva un objeto para evitar el 405/404 erróneo
        when(envioService.buscarPorId(1L)).thenReturn(crearEnvio("Cali", "Servientrega", "En camino"));

        mockMvc.perform(get("/envios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destino").value("Cali"));
    }

    @Test
    void buscarPorIdDebeRetornar404CuandoNoExiste() throws Exception {
        // CORRECCIÓN: Retorna null para validar la lógica del ResponseEntity.notFound()
        when(envioService.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/envios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearDebeRetornar200YAsignarEstadoPendiente() throws Exception {
        Envio respuesta = crearEnvio("Medellin", "Coordinadora", "Pendiente");
        when(envioService.guardar(any(Envio.class))).thenReturn(respuesta);

        Envio request = crearEnvio("Medellin", "Coordinadora", "Entregado");

        mockMvc.perform(post("/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Pendiente"));
    }

    @Test
    void actualizarDebeRetornar200CuandoExiste() throws Exception {
        Envio actual = crearEnvio("Bogota", "DHL", "Pendiente");
        // CORRECCIÓN: buscarPorId debe devolver un objeto para que el controlador no de 404[cite: 1]
        when(envioService.buscarPorId(10L)).thenReturn(actual);
        when(envioService.guardar(any(Envio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Envio request = crearEnvio("Pasto", "Interrapidisimo", "En camino");

        mockMvc.perform(put("/envios/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destino").value("Pasto"));
    }

    @Test
    void actualizarDebeRetornar404CuandoNoExiste() throws Exception {
        // CORRECCIÓN: El mock devuelve null para forzar el estado 404 en el controlador[cite: 1]
        when(envioService.buscarPorId(11L)).thenReturn(null);

        Envio request = crearEnvio("Pasto", "Interrapidisimo", "En camino");

        mockMvc.perform(put("/envios/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarDebeRetornar200CuandoExiste() throws Exception {
        when(envioService.existePorId(2L)).thenReturn(true);
        doNothing().when(envioService).eliminar(2L);

        mockMvc.perform(delete("/envios/2"))
                .andExpect(status().isOk());

        verify(envioService).eliminar(2L);
    }

    @Test
    void eliminarDebeRetornar404CuandoNoExiste() throws Exception {
        when(envioService.existePorId(3L)).thenReturn(false);
        mockMvc.perform(delete("/envios/3"))
                .andExpect(status().isNotFound());
    }


    private Envio crearEnvio(String destino, String transportista, String estado) {
        Envio envio = new Envio();
        envio.setDestino(destino);
        envio.setTransportista(transportista);
        envio.setEstado(estado);
        return envio;
    }
}