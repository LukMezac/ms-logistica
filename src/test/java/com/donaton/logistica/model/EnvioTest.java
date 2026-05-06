package com.donaton.logistica.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EnvioTest {

    @Test
    void debePermitirValoresValidos() {
        Envio envio = new Envio();
        envio.setDestino("Bogota");
        envio.setTransportista("DHL");
        envio.setEstado("Pendiente");

        assertEquals("Bogota", envio.getDestino());
        assertEquals("DHL", envio.getTransportista());
        assertEquals("Pendiente", envio.getEstado());
        assertNull(envio.getId());
    }

    @Test
    void debePermitirValoresInvalidosSinRomper() {
        Envio envio = new Envio();
        envio.setDestino("");
        envio.setTransportista("   ");
        envio.setEstado("###");

        assertEquals("", envio.getDestino());
        assertEquals("   ", envio.getTransportista());
        assertEquals("###", envio.getEstado());
    }

    @Test
    void debePermitirValoresNull() {
        Envio envio = new Envio();
        envio.setDestino(null);
        envio.setTransportista(null);
        envio.setEstado(null);

        assertNull(envio.getDestino());
        assertNull(envio.getTransportista());
        assertNull(envio.getEstado());
    }
}
