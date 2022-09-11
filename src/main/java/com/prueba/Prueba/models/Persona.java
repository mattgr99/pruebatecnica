package com.prueba.Prueba.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private String nombre;
    private String genero;
    private int edad;
    private int identificacion;
    private String direccion;
    private String telefono;
}
