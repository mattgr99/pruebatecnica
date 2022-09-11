package com.prueba.Prueba.controllers;

import com.prueba.Prueba.dto.ClienteDto;
import com.prueba.Prueba.dto.CuentaDto;
import com.prueba.Prueba.dto.ResponseDto;
import com.prueba.Prueba.models.Cliente;
import com.prueba.Prueba.models.Cuenta;
import com.prueba.Prueba.services.IClienteService;
import com.prueba.Prueba.services.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cuentas")
@RequiredArgsConstructor
public class CuentaController {
    private final ICuentaService cuentaService;
    private final IClienteService clienteService;

    @GetMapping("")
    public ResponseDto findAll(){
        ResponseDto response = new ResponseDto();
        List<Cuenta> cuentas = cuentaService.findAll();
        CuentaDto cuentaDto = new CuentaDto();
        List<CuentaDto> cuentasLista = new ArrayList<>();

        if (cuentas.isEmpty()) {
            response.setError(HttpStatus.NO_CONTENT.toString());
            response.setMessage("No existen cuentas registradas");
            response.setCorrectProcess(false);
            response.setData(null);
        }
        else {
            for (Cuenta a: cuentas){
                cuentaDto.setNumerocuenta(a.getNumerocuenta());
                cuentaDto.setTipoCuenta(a.getTipoCuenta());
                cuentaDto.setSaldo(a.getSaldo());
                cuentaDto.setEstado(a.isEstado());
                cuentaDto.setNombre(a.getCliente().getNombre());
                cuentasLista.add(cuentaDto);
                cuentaDto = new CuentaDto();
            }
            response.setError(null);
            response.setMessage(HttpStatus.OK.toString());
            response.setCorrectProcess(true);
            response.setData(cuentasLista);
        }
        return response;
    }

    @PostMapping("")
    public ResponseDto guardarCuenta(@RequestBody Cuenta cuenta) {
        ResponseDto response = new ResponseDto();
        try{
            Cliente cliente = clienteService.get(cuenta.getCliente().getClienteid());
            cuenta.setCliente(cliente);
            cuentaService.save(cuenta);
            response.setError(null);
            response.setMessage("Guardar cuenta");
            response.setCorrectProcess(true);
            response.setData(cliente);

            return response;

        }catch (Exception e){
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("An exception has occurred");
            response.setCorrectProcess(false);
            response.setData(e);
            return response;
        }
    }

    @PutMapping("")
    public ResponseDto actualizarCuenta(@RequestBody Cuenta cuenta) {
        ResponseDto response = new ResponseDto();
        try{
            Cuenta cuentaUpdate = cuentaService.get(cuenta.getCuentaid());
            cuentaUpdate.setNumerocuenta(cuenta.getNumerocuenta());
            cuentaUpdate.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaUpdate.setSaldo(cuenta.getSaldo());
            cuentaUpdate.setEstado(cuentaUpdate.isEstado());
            cuentaService.save(cuentaUpdate);
            response.setError(null);
            response.setMessage("cuenta actualizada");
            response.setCorrectProcess(true);
            response.setData(cuenta);

            return response;

        }catch (Exception e){
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("An exception has occurred");
            response.setCorrectProcess(false);
            response.setData(e);
            return response;
        }
    }

    @DeleteMapping("{id}")
    public ResponseDto eliminarCuenta(@PathVariable(value="id") Long id) {
        ResponseDto response = new ResponseDto();
        try{
            cuentaService.delete(id);
            response.setError(null);
            response.setMessage("Cuenta Eliminada");
            response.setCorrectProcess(true);
            response.setData(true);

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
