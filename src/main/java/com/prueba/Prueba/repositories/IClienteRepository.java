package com.prueba.Prueba.repositories;

import com.prueba.Prueba.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente,Long> {
}
