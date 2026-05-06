package com.donaton.logistica.service;

import com.donaton.logistica.model.Envio;
import com.donaton.logistica.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @Test
    void listarDebeRetornarTodosLosEnvios() {
        List<Envio> envios = List.of(crearEnvio("Bogota", "DHL", "Pendiente"));
        when(envioRepository.findAll()).thenReturn(envios);

        List<Envio> resultado = envioService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Bogota", resultado.get(0).getDestino());
    }

    @Test
    void guardarDebePersistirYRetornarEnvio() {
        Envio envio = crearEnvio("Medellin", "Coordinadora", "Pendiente");
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.guardar(envio);

        assertSame(envio, resultado);
        verify(envioRepository).save(envio);
    }

    @Test
    void buscarPorIdDebeRetornarEnvioCuandoExiste() {
        Envio envio = crearEnvio("Cali", "Servientrega", "En camino");
        when(envioRepository.findById(10L)).thenReturn(Optional.of(envio));

        Envio resultado = envioService.buscarPorId(10L);

        assertSame(envio, resultado);
    }

    @Test
    void buscarPorIdDebeRetornarNullCuandoNoExiste() {
        when(envioRepository.findById(99L)).thenReturn(Optional.empty());

        Envio resultado = envioService.buscarPorId(99L);

        assertNull(resultado);
    }

    @Test
    void existePorIdDebeRetornarTrueCuandoExiste() {

        when(envioRepository.existsById(4L)).thenReturn(true);
        boolean resultado = envioService.existePorId(4L);
        assertTrue(resultado);
    }

    @Test
    void existePorIdDebeRetornarFalseCuandoNoExiste() {

        when(envioRepository.existsById(5L)).thenReturn(false);


        boolean resultado = envioService.existePorId(5L);

        assertFalse(resultado);
    }

    @Test
    void eliminarDebeBorrarPorId() {
        envioService.eliminar(7L);

        verify(envioRepository).deleteById(7L);
    }

    private Envio crearEnvio(String destino, String transportista, String estado) {
        Envio envio = new Envio();
        envio.setDestino(destino);
        envio.setTransportista(transportista);
        envio.setEstado(estado);
        return envio;
    }
}