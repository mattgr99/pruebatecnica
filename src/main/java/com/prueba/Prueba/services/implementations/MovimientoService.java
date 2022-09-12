package com.prueba.Prueba.services.implementations;

import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.models.Movimiento;
import com.prueba.Prueba.repositories.IMovimientoRepository;
import com.prueba.Prueba.services.IMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MovimientoService implements IMovimientoService {
    private final IMovimientoRepository movimientoRepository;
    @Override
    public Movimiento save(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
        return movimiento;
    }

    @Override
    public Movimiento get(Long id) {
        return movimientoRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> findAll() {
        return (List<Movimiento>) movimientoRepository.findAll();
    }

    @Override
    public List<Movimiento> findByCuentaAndFecha(Cuenta cuenta, LocalDate fecha) {
        return movimientoRepository.findByCuentaAndFecha(cuenta, fecha);
    }

    @Override
    public List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate from, LocalDate to) {
        return movimientoRepository.findByCuentaAndFechaBetween(cuenta, from, to);
    }
}
