package com.dcamarero.proyectomvc.controller;

import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.User;
import com.dcamarero.proyectomvc.services.ClienteService;
import com.dcamarero.proyectomvc.services.SalesService;
import com.dcamarero.proyectomvc.services.UserService;
import com.dcamarero.proyectomvc.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestController
@RequestMapping("/")
public class LoginController
{
    @Autowired
    UserService userService;

    @Autowired
    ClienteService clientService;

    @Autowired
    SalesService salesService;

    @RequestMapping(value = "/login/admin", method = RequestMethod.GET)
    public ResponseEntity<Object> loginAdmin(@RequestParam String email,
                                             @RequestParam String pass)
    {
        User userDTO;
        ResponseEntity<Object> response;
        try
        {
            userDTO = userService.getUserByEmail(email);
            if (UserUtils.comparePass(pass, userDTO))
            {
                userService.updateLoginDate(userDTO);
                response = new ResponseEntity<Object>(userDTO, HttpStatus.OK);
            } else
            {
                response = new ResponseEntity<Object>("Contraseña o Usuario incorrecto", HttpStatus.FORBIDDEN);
            }

        }
        catch (EntityNotFoundException e)
        {
//            log.error("Error " + e.getMessage());
            response = new ResponseEntity<Object>("Not found on database", HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<Object> loginClient(@RequestParam String email,
                                              @RequestParam String pass)
    {
        Cliente clienteDTO;
        ResponseEntity<Object> response;
        try
        {
            clienteDTO = clientService.getClienteByEmail(email);
            if (UserUtils.comparePass(pass, clienteDTO))
            {
//                log.info("login true");
                clientService.updateLoginDate(clienteDTO);

                salesService.checkEditando(clienteDTO);


                response = new ResponseEntity<Object>(clienteDTO, HttpStatus.OK);

            } else
            {
//                log.info("login false?");
                response = new ResponseEntity<Object>("Contraseña o Usuario incorrecto", HttpStatus.FORBIDDEN);
            }

        }
        catch (EntityNotFoundException e)
        {
//            log.error("Error :" + e.getMessage());
            response = new ResponseEntity<Object>("Not found on database", HttpStatus.NO_CONTENT);
        }
        return response;
    }
}
