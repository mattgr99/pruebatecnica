package com.prueba.Prueba.repositories;
import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.models.Movimiento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoRepository extends JpaRepository<Movimiento,Long> {
    List<Movimiento> findByFecha(LocalDate fecha);
    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate from, LocalDate to);
}
