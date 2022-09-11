package com.prueba.Prueba.dto;

import com.prueba.Prueba.models.Cliente;
import lombok.Data;

@Data
public class CuentaDto {
    private String numerocuenta;
    private String tipoCuenta;
    private double saldo;
    private boolean estado;
    private String nombre;
}
