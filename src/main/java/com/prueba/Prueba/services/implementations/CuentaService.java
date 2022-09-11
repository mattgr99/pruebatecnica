package com.prueba.Prueba.services.implementations;

import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.repositories.ICuentaRepository;
import com.prueba.Prueba.services.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CuentaService implements ICuentaService {
    private final ICuentaRepository cuentaRepository;

    @Override
    public Cuenta save(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
        return cuenta;
    }

    @Override
    public Cuenta get(Long id) {
        return cuentaRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> findAll() {
        return (List<Cuenta>) cuentaRepository.findAll();
    }

    @Override
    public List<Cuenta> findByNumerocuenta(String nro) {
        return (List<Cuenta>) cuentaRepository.findByNumerocuenta(nro);
    }
}
