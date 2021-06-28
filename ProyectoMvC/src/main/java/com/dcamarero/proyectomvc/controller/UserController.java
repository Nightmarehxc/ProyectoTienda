package com.dcamarero.proyectomvc.controller;

import com.dcamarero.proyectomvc.models.User;
import com.dcamarero.proyectomvc.models.enums.UserType;
import com.dcamarero.proyectomvc.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getAllUsers()
    {
        Set<User> userDTOS = userService.getAllUsers();
        ResponseEntity<Set<User>> response = new ResponseEntity<Set<User>>(userDTOS, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/getByDNI", method = RequestMethod.GET)
    public ResponseEntity<User> getByDNI(@RequestParam String dni)
    {
        User userDTO = userService.getUserByDni(dni);
        ResponseEntity<User> response = new ResponseEntity<User>(userDTO, HttpStatus.OK);

        return response;
    }

    @RequestMapping(value = "/getByEmail", method = RequestMethod.GET)
    public ResponseEntity<User> getByEmail(@RequestParam String email)
    {

        ResponseEntity<User> response;

        try
        {
            User responseObject = userService.getUserByEmail(email);
            response = new ResponseEntity<User>(responseObject, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            e.getMessage();
            User userDTO = null;
            response = new ResponseEntity<User>(userDTO, HttpStatus.NO_CONTENT);
        }

        return response;

    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ResponseEntity<Object> saveUser(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String password,
                                           @RequestParam int userType,
                                           @RequestParam String dni,
                                           @RequestParam String surname,
                                           @RequestParam String phone
    )
    {
        ResponseEntity<Object> response;

//        log.info("UserController.Save Start");

        User userDTO;
        if (userType == 0)
        {
            userDTO = new User(name, email, password, UserType.REGULAR, dni, surname, phone, LocalDateTime.now(), true);
            userDTO.setName(name);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setDni(dni);
            userDTO.setSurname(surname);
            userDTO.setPhone(phone);
            userDTO.setActiveUser(true);
            userService.save(userDTO);
        } else if (userType == 1)
        {

            userDTO = new User(name, email, password, UserType.ADMIN, dni, surname, phone, LocalDateTime.now(), true);
            userService.save(userDTO);
        }

        response = new ResponseEntity<Object>("Usuario Creado", HttpStatus.OK);


        return response;
    }

}
