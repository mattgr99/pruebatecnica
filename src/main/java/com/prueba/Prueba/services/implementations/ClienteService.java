package com.prueba.Prueba.services.implementations;

import com.prueba.Prueba.models.Cliente;
import com.prueba.Prueba.repositories.IClienteRepository;
import com.prueba.Prueba.services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class ClienteService implements IClienteService {
    private final IClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public Cliente get(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}
