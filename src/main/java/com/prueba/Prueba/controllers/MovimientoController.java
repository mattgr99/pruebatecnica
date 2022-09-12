package com.prueba.Prueba.controllers;

import com.prueba.Prueba.dto.CuentaDto;
import com.prueba.Prueba.dto.MovimientoDto;
import com.prueba.Prueba.dto.ResponseDto;
import com.prueba.Prueba.models.Cliente;
import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.models.Movimiento;
import com.prueba.Prueba.services.IClienteService;
import com.prueba.Prueba.services.ICuentaService;
import com.prueba.Prueba.services.IMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final ICuentaService cuentaService;
    private final IClienteService clienteService;
    private final IMovimientoService movimientoService;

    @GetMapping("")
    public ResponseDto findAll(){
        ResponseDto response = new ResponseDto();
        List<Movimiento> movimientos = movimientoService.findAll();
        MovimientoDto movimientoDto = new MovimientoDto();
        List<MovimientoDto> movimientosLista = new ArrayList<>();

        if (movimientos.isEmpty()) {
            response.setError(HttpStatus.NO_CONTENT.toString());
            response.setMessage("No existen movimientos registrados");
            response.setCorrectProcess(false);
            response.setData(null);
        }
        else {
            for (Movimiento a: movimientos){
                movimientoDto.setTipoMovimiento(a.getTipoMovimiento());
                movimientoDto.setValor(a.getValor());
                movimientoDto.setSaldo(a.getCuenta().getSaldo());
                movimientoDto.setTipoCuenta(a.getCuenta().getTipoCuenta());
                movimientoDto.setNumeroCuenta(a.getCuenta().getNumerocuenta());
                movimientosLista.add(movimientoDto);
                movimientoDto = new MovimientoDto();
            }
            response.setError(null);
            response.setMessage(HttpStatus.OK.toString());
            response.setCorrectProcess(true);
            response.setData(movimientosLista);
        }
        return response;
    }

    @GetMapping("/reporte/{nro}/from/{from}/to/{to}")
    public ResponseDto reporte(@PathVariable (value="nro") String nro, @PathVariable(value = "from") String from, @PathVariable(value = "to") String to){
        ResponseDto response = new ResponseDto();
        List<Cuenta> cuenta = cuentaService.findByNumerocuenta(nro);

        List<Movimiento> movimientos = movimientoService.findByCuentaAndFechaBetween(cuenta.get(0),LocalDate.parse(from),LocalDate.parse(to));

        if (movimientos.isEmpty()) {
            response.setError(HttpStatus.NO_CONTENT.toString());
            response.setMessage("No existen movimientos en ese rango de fechas");
            response.setCorrectProcess(false);
            response.setData(null);
        }
        else {
            response.setError(null);
            response.setMessage(HttpStatus.OK.toString());
            response.setCorrectProcess(true);
            response.setData(movimientos);
        }
        return response;
    }

    @PostMapping("")
    public ResponseDto guardarMovimiento(@RequestBody Movimiento movimiento) {
        ResponseDto response = new ResponseDto();
        try{
            Cuenta cuenta = cuentaService.get(movimiento.getCuenta().getCuentaid());
            movimiento.setCuenta(cuenta);
            if (((movimiento.getCuenta().getSaldo()>0) ) || (movimiento.getTipoMovimiento().toUpperCase().equals("DEPOSITO"))){
                movimiento.setFecha(LocalDate.now());
                if (movimiento.getTipoMovimiento().toUpperCase().equals("RETIRO")){
                    List<Movimiento> movimientosFecha = movimientoService.findByCuentaAndFecha(movimiento.getCuenta(), movimiento.getFecha());

                    double limiteDiario = 0;
                    if ((!movimientosFecha.isEmpty())){
                        for (Movimiento mov : movimientosFecha){
                            if (mov.getTipoMovimiento().toUpperCase().equals("RETIRO"))
                                limiteDiario += (mov.getValor()*-1);
                        }
                    }

                    if (((limiteDiario + (movimiento.getValor()*-1)) < 1000)){
                        if(((movimiento.getValor()*-1)<= movimiento.getCuenta().getSaldo())){
                            movimientoService.save(movimiento);
                            cuenta.setSaldo(cuenta.getSaldo() + movimiento.getValor());
                            cuentaService.save(cuenta);
                            response.setError(null);
                            response.setMessage("Movimiento exitoso");
                            response.setCorrectProcess(true);
                            response.setData(movimiento);
                        }else {
                            response.setError(null);
                            response.setMessage("Saldo no disponible");
                            response.setCorrectProcess(true);
                            response.setData(null);
                        }

                        response.setData(movimiento);
                    }else{
                        response.setError(null);
                        response.setMessage("Cupo diario Excedido");
                        response.setCorrectProcess(true);
                        response.setData(null);
                    }
                }else{
                    movimientoService.save(movimiento);
                    cuenta.setSaldo(cuenta.getSaldo() + movimiento.getValor());
                    cuentaService.save(cuenta);
                    response.setError(null);
                    response.setMessage("Movimiento exitoso");
                    response.setCorrectProcess(true);
                    response.setData(movimiento);
                }

            }else {

                response.setError(null);
                response.setMessage("Saldo no disponible");
                response.setCorrectProcess(true);
                response.setData(null);
            }

            return response;

        }catch (Exception e){
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("An exception has occurred");
            response.setCorrectProcess(false);
            response.setData(e);
            return response;
        }
    }

}
