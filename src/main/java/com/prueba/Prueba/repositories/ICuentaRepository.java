package com.prueba.Prueba.repositories;

import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ICuentaRepository extends JpaRepository<Cuenta,Long> {
    List<Cuenta> findByNumerocuenta(String nro);
}
