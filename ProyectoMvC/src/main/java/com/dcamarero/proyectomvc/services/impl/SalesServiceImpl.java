package com.dcamarero.proyectomvc.services.impl;

import com.dcamarero.proyectomvc.dao.CartDAO;
import com.dcamarero.proyectomvc.dao.CartEntryDAO;
import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.CartEntry;
import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.Item;
import com.dcamarero.proyectomvc.models.enums.EstadoType;
import com.dcamarero.proyectomvc.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class SalesServiceImpl implements SalesService
{
    @Autowired
    CartEntryDAO cartEntryDAO;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Override
    public Set<Cart> checkEditando(Cliente clienteDTO)
    {
//        log.info("SalesService.checkEditando");
        Set<Cart> cartSet = new HashSet<>();
        try
        {
//            log.info("cartDato.FindAll By Cliente Y Estado");
            cartDAO.findAllByClienteAndEstado(clienteDTO, EstadoType.EDITANDO).forEach(cartSet::add);

        }
        catch (EntityNotFoundException e)
        {
//            log.info("Creando nuevo carrito");
            createNewCart(clienteDTO);
        }
        return cartSet;
    }

    @Override
    public Cart createCart(Cliente client)
    {
//        log.info(" START SalesService CreateCart");
        Cart newCart = new Cart();
        newCart.setSeller(userService.getUserByName("SHOP"));
        newCart.setEstado(EstadoType.EDITANDO);
        newCart.setCliente(client);
        newCart.setSellDate(LocalDateTime.now());
        cartDAO.save(newCart);
        client.getCarts().add(newCart);
//        log.info(" SAVE Client CreateCart");
        clienteService.save(client);

//        log.info(client.getCarts().toString());
        return newCart;
    }

    @Override
    public Cart createNewCart(Cliente client)
    {
//        log.info(" START SalesService CreateCart");
        Cart newCart = new Cart();
        newCart.setSeller(userService.getUserByName("SHOP"));
        newCart.setEstado(EstadoType.EDITANDO);
        newCart.setCliente(client);
        newCart.setSellDate(LocalDateTime.now());
        cartDAO.save(newCart);
//        log.info("Añade nuevo carro");
//        log.info(String.valueOf(client.getCarts()));
        if (client.getCarts().size() != 0)
        {


            client.getCarts().add(newCart);
//            log.info(" SAVE Client CreateCart");
//            clienteService.save(client);

//            log.info(client.getCarts().toString());
        } else
        {

        }
        return newCart;
    }


    @Override
    public Cart updateCart(String clientID, int cant, long itemID)
    {
        Cart cart = null;
        Item item = itemService.getItemById(itemID);
        if (itemService.checkAvailabilityStock(item, 1))
        {
            Cliente cliente = clienteService.getClienteByDNI(clientID);
            cart = cartDAO.findAllByClienteAndEstado(cliente, EstadoType.EDITANDO).iterator().next();
            CartEntry newItem = new CartEntry();//añadimos el nuevo item a la lista
            newItem.setItem(item);
            newItem.getAmount();
            newItem.setPrice(item.getPrice());
            cart.setCliente(clienteService.getClienteByDNI(clientID));
            cart.setSellDate(LocalDateTime.now());
            cart.getCartEntries().add(newItem);
            cart.setEstado(EstadoType.EDITANDO);
            cart.setTotal(cart.getTotal() + newItem.getAmount() * newItem.getPrice());
            cartDAO.save(cart);
        } else
        {
//            log.info("Error de Stock");
        }

        return cart;
    }
}
