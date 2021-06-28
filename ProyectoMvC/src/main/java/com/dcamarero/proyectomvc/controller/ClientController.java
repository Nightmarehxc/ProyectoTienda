package com.dcamarero.proyectomvc.controller;

import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/clientManager")
@Api(value = "ClientController", produces =
        MediaType.APPLICATION_JSON_VALUE)
public class ClientController
{
    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation("/getAll ")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response =
            ClientController.class)})
    public ResponseEntity<Set<Cliente>> getAllUsers()
    {
        ResponseEntity<Set<Cliente>> response;
        Set<Cliente> clienteDTOS = null;
        try
        {
            clienteDTOS = clienteService.getAllClients();
            response = new ResponseEntity<Set<Cliente>>(clienteDTOS, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
//            log.error("Error " + e.getMessage());
            response = new ResponseEntity<Set<Cliente>>(clienteDTOS, HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @RequestMapping(value = "/getByDNI", method = RequestMethod.GET)
    public ResponseEntity<Object> getByDNI(@RequestParam String dni)
    {
        ResponseEntity<Object> response;
        try
        {
            Cliente userDTO = clienteService.getClienteByDNI(dni);
            response = new ResponseEntity<Object>(userDTO, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            response = new ResponseEntity<Object>("Usuario no encontrado", HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @RequestMapping(value = "/getByEmail", method = RequestMethod.GET)
    public ResponseEntity<Object> getByEmail(@RequestParam String email)
    {
        ResponseEntity<Object> response;
        try
        {
            Cliente responseObject = clienteService.getClienteByEmail(email);
            response = new ResponseEntity<Object>(responseObject, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            e.getMessage();
//            Cliente userDTO = null;
            response = new ResponseEntity<Object>("Usuario no encontrado", HttpStatus.NO_CONTENT);
        }

        return response;

    }

    @RequestMapping(value = "/saveNewClient", method = RequestMethod.GET)
    public ResponseEntity<Object> saveClient(@RequestParam String name,
                                             @RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam String dni,
                                             @RequestParam String surname,
                                             @RequestParam String phone,
                                             @RequestParam String genderType

    )
    {
        ResponseEntity<Object> response;
//        log.info("UserController.Save Start");
        Cliente cliente;
        try
        {
            cliente = new Cliente();
            cliente.setName(name);
            cliente.setEmail(email);
            cliente.setPassword(password);
            cliente.setDni(dni);
            cliente.setSurname(surname);
            cliente.setPhone(phone);
            cliente.setActive(true);
            cliente.setJoinDate(LocalDateTime.now());
            cliente.setCarts(null);
            cliente.setGenderType(genderType);
            clienteService.saveNewUser(cliente);


            response = new ResponseEntity<Object>("Usuario Creado", HttpStatus.OK);
        }
        catch (EntityExistsException e)
        {
            response = new ResponseEntity<Object>("El usuario ya existe", HttpStatus.IM_USED);
        }
        return response;
    }

}
