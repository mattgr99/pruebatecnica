package com.prueba.Prueba.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private boolean estado;
}
