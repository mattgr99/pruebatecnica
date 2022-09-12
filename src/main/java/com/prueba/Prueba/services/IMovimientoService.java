package com.prueba.Prueba.services;



import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.models.Movimiento;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoService {
    Movimiento save(Movimiento movimiento);
    Movimiento get(Long id);
    void delete(Long id);
    List<Movimiento> findAll();
    List<Movimiento> findByCuentaAndFecha(Cuenta cuenta, LocalDate fecha);
    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate from, LocalDate to);
}
