package com.prueba.Prueba.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MovimientoDto {
    private String numeroCuenta;
    private String tipoCuenta;
    private boolean estado;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
}
