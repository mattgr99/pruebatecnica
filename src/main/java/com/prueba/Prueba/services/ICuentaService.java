package com.prueba.Prueba.services;

import com.prueba.Prueba.models.Cliente;
import com.prueba.Prueba.models.Cuenta;

import java.util.List;

public interface ICuentaService {
    Cuenta save(Cuenta cuenta);
    Cuenta get(Long id);
    void delete(Long id);
    List<Cuenta> findAll();
    List<Cuenta> findByNumerocuenta(String nro);
}
