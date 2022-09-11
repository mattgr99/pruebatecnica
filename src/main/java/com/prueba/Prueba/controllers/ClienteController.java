package com.prueba.Prueba.controllers;

import com.prueba.Prueba.dto.ClienteDto;
import com.prueba.Prueba.dto.ResponseDto;
import com.prueba.Prueba.models.Cliente;
import com.prueba.Prueba.services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final IClienteService clienteService;

    @GetMapping("")
    public ResponseDto findAll(){
        ResponseDto response = new ResponseDto();
        List<Cliente> clientes = clienteService.findAll();
        ClienteDto clienteDto = new ClienteDto();
        List<ClienteDto> clientesLista = new ArrayList<>();

        if (clientes.isEmpty()) {
            response.setError(HttpStatus.NO_CONTENT.toString());
            response.setMessage("No se encontro clientes");
            response.setCorrectProcess(false);
            response.setData(null);
        }
        else {
            for (Cliente a: clientes){
                clienteDto.setNombre(a.getNombre());
                clienteDto.setDireccion(a.getDireccion());
                clienteDto.setTelefono(a.getTelefono());
                clienteDto.setContrasenia(a.getContrasenia());
                clienteDto.setEstado(a.isEstado());
                clientesLista.add(clienteDto);
                clienteDto = new ClienteDto();
            }
            response.setError(null);
            response.setMessage(HttpStatus.OK.toString());
            response.setCorrectProcess(true);
            response.setData(clientesLista);
        }
        return response;
    }

    @PostMapping("")
    public ResponseDto guardarCliente(@RequestBody Cliente cliente) {
        ResponseDto response = new ResponseDto();
        try{
            clienteService.save(cliente);
            response.setError(null);
            response.setMessage("Guardar cliente");
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
    public ResponseDto actualizarCliente(@RequestBody Cliente cliente) {
        ResponseDto response = new ResponseDto();
        try{
            Cliente clienteUpdate = clienteService.get(cliente.getClienteid());
            clienteUpdate.setNombre(cliente.getNombre());
            clienteUpdate.setDireccion(cliente.getDireccion());
            clienteUpdate.setEdad(cliente.getEdad());
            clienteUpdate.setGenero(cliente.getGenero());
            clienteUpdate.setTelefono(cliente.getTelefono());
            clienteUpdate.setIdentificacion(cliente.getIdentificacion());
            clienteUpdate.setContrasenia(cliente.getContrasenia());
            clienteUpdate.setEstado(cliente.isEstado());
            clienteService.save(clienteUpdate);
            response.setError(null);
            response.setMessage("cliente actualizado");
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

    @DeleteMapping("{id}")
    public ResponseDto eliminarCliente(@PathVariable(value="id") Long id) {
        ResponseDto response = new ResponseDto();
        try{
            clienteService.delete(id);
            response.setError(null);
            response.setMessage("Cliente Eliminado");
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
