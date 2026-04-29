package com.donaton.logistica.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String destino;
    @Setter
    private String transportista;
    @Setter
    private String estado;

    public Envio() {}

    public Long getId() { return id; }

    public String getDestino() { return destino; }

    public String getTransportista() { return transportista; }

    public String getEstado() { return estado; }
}