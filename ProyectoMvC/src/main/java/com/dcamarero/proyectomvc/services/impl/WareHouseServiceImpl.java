package com.dcamarero.proyectomvc.services.impl;

import com.dcamarero.proyectomvc.dao.CartDAO;
import com.dcamarero.proyectomvc.dao.CartEntryDAO;
import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.CartEntry;
import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.User;
import com.dcamarero.proyectomvc.models.enums.EstadoType;
import com.dcamarero.proyectomvc.services.ItemService;
import com.dcamarero.proyectomvc.services.UserService;
import com.dcamarero.proyectomvc.services.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class WareHouseServiceImpl implements WarehouseService
{
    @Autowired
    ItemService itemService;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    UserService userService;

    @Autowired
    CartEntryDAO cartEntryDAO;

    @Override
    public Set<Cart> getAllCartsByClient(Cliente client)
    {
        Set<Cart> result = new HashSet<>();
        cartDAO.findAllByCliente(client).forEach(result::add);
        return result;

    }

    @Override
    public Set<Cart> getAllCartsByUser(String dni)
    {
//        log.info("Start WareHouseServiceImpl.getAllCartsByUser");
        User userDTO = userService.getUserByDni(dni);
        Set<Cart> result = new HashSet<>();
        try
        {
//            log.info("Start findAllBySeller");
            cartDAO.findAllBySeller(userDTO).forEach(result::add);
        }
        catch (EntityNotFoundException e)
        {
//            log.error("ERROR" + e.getMessage());
        }
        return result;
    }

    @Override
    public Set<Cart> getAllCartsByState(int estado) throws EntityNotFoundException
    {
//        log.info(String.valueOf(estado));
        Set<Cart> cartSet = new HashSet<>();
        switch (estado)
        {
            case 0:
                cartDAO.findAllByEstado(EstadoType.W_PAYMENT_TRANSFER).forEach(cartSet::add);
            case 1:
                cartDAO.findAllByEstado(EstadoType.W_CASH_ON_DELIVERY).forEach(cartSet::add);
            case 2:
                cartDAO.findAllByEstado(EstadoType.PAGOACEPTADO).forEach(cartSet::add);
            case 3:
                cartDAO.findAllByEstado(EstadoType.PREPARANDO).forEach(cartSet::add);
            case 4:
                cartDAO.findAllByEstado(EstadoType.ENVIADO).forEach(cartSet::add);
            case 5:
                cartDAO.findAllByEstado(EstadoType.EDITANDO).forEach(cartSet::add);
        }
        return cartSet;

    }

    @Override
    public Cart getCart(long id)
    {
        final Optional<Cart> response = cartDAO.findById(id);
        return response.orElse(null);
    }

    @Override
    public Cart updateCart(long id, int state, String userMod)
    {
        Cart cart = null;
        try
        {
            User userDTO = userService.getUserByDni(userMod);
            cart = getCart(id);
            cart.setSeller(userDTO);
            switch (state)
            {
                case 0:
                    cart.setEstado(EstadoType.W_PAYMENT_TRANSFER);
                case 1:
                    cart.setEstado(EstadoType.W_CASH_ON_DELIVERY);
                case 2:
                    cart.setEstado(EstadoType.PREPARANDO);
                case 3:
                    cart.setEstado(EstadoType.ENVIADO);
                case 4:
                    cart.setEstado(EstadoType.RECHAZADO);
            }
            cartDAO.save(cart);
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
//            log.error("Error: " + entityNotFoundException.getMessage());
        }
        return cart;
    }

    @Override
    public Set<CartEntry> getAllCartEntriesByCart(Cart cart)
    {

        Set<CartEntry> cartEntries = new HashSet<>();
        try
        {
            cartEntryDAO.findAllByCart(cart).forEach(cartEntries::add);
        }
        catch (EntityNotFoundException e)
        {
//            log.info("el carrito no tiene entradas");
        }
        return cartEntries;
    }

    @Override
    public Set<Cart> getCartByClientANDEstado(EstadoType estadoType, Cliente c)
    {
        Set<Cart> cartSet = new HashSet<>();
        try
        {
            cartDAO.findAllByClienteAndEstado(c, estadoType).forEach(cartSet::add);
        }
        catch (EntityNotFoundException e)
        {
//            log.error(e.getMessage());
        }
        return cartSet;
    }

}
