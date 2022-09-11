package com.prueba.Prueba.services;

import com.prueba.Prueba.models.Cliente;

import java.util.List;

public interface IClienteService {
    Cliente save(Cliente cliente);
    Cliente get(Long id);
    void delete(Long id);
    List<Cliente> findAll();
}
