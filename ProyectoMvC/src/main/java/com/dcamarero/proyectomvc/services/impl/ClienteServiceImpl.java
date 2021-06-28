package com.dcamarero.proyectomvc.services.impl;

import com.dcamarero.proyectomvc.dao.ClientDAO;
import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.services.ClienteService;
import com.dcamarero.proyectomvc.services.SalesService;
import com.dcamarero.proyectomvc.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService
{
    @Autowired
    ClientDAO clientDAO;

    @Autowired
    SalesService salesService;


    @Override
    public void save(Cliente clienteDTO)
    {
        try
        {
            clientDAO.save(clienteDTO);
        }
        catch (EntityExistsException e)
        {
//            log.info("La entidad ya existe");
        }

    }

    @Override
    public void saveNewUser(Cliente clienteDTO)
    {
        try
        {
            clienteDTO.setPassword(
                    UserUtils.hashPassword(clienteDTO.getPassword()));

            clientDAO.save(clienteDTO);
        }
        catch (EntityExistsException e)
        {
//            log.info("La entidad ya existe");
        }

    }

    @Override
    public Cliente getClienteByDNI(String dni)
    {
        final Optional<Cliente> response = clientDAO.findByDni(dni);
        return response.orElse(null);
    }

    @Override
    public Cliente getClienteByEmail(String email)
    {
        final Optional<Cliente> response = clientDAO.findByEmail(email);
        return response.orElse(null);
    }

    @Override
    public void updateLoginDate(Cliente clienteDTO)
    {
//        log.info("Start ClienteService.updateLoginDate");
        clienteDTO.setLastLogin(LocalDateTime.now());
        clientDAO.save(clienteDTO);
    }

    @Override
    public Set<Cliente> getAllClients()
    {
        Set<Cliente> result;
        try
        {
            result = new HashSet<>();
            clientDAO.findAll().forEach(result::add);

        }
        catch (Exception e)
        {
            result = new HashSet<>();
//            clientDAO.findAll().forEach(result::add);

        }
        return result;
    }


}
