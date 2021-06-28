package com.dcamarero.proyectomvc.controller;

import com.dcamarero.proyectomvc.dao.CartEntryDAO;
import com.dcamarero.proyectomvc.exception.InsufficientStockException;
import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.CartEntry;
import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.Item;
import com.dcamarero.proyectomvc.models.enums.ItemType;
import com.dcamarero.proyectomvc.services.ClienteService;
import com.dcamarero.proyectomvc.services.ItemService;
import com.dcamarero.proyectomvc.services.UserService;
import com.dcamarero.proyectomvc.services.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/warehouse")
public class WareHouseController
{
    @Autowired
    ItemService itemService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    UserService userService;

    @Autowired
    CartEntryDAO cartEntryDAO;

    @Autowired
    ClienteService clienteService;


    @RequestMapping(value = "/items/saveItem", method = RequestMethod.GET)
    public ResponseEntity<Object> saveItem(@RequestParam ItemType itemType, @RequestParam String name, @RequestParam int amount, @RequestParam double price)
    {
        ResponseEntity<Object> response = null;
        try
        {
            Item newItem = new Item();
            newItem.setName(name);
            newItem.setItemType(itemType);
            newItem.setAmount(amount);
            newItem.setPrice(price);
            newItem.setLastMod(LocalDateTime.now());
            newItem.setLastUserModification("SHOP");
            itemService.save(newItem);
            response = new ResponseEntity<Object>("Item " + name + "Creado correctamente", HttpStatus.OK);
        }
        catch (EntityExistsException e)
        {
//            log.info(e.getMessage());
            response = new ResponseEntity<Object>("Item " + name + "Creado correctamente", HttpStatus.IM_USED);
        }
        return response;
    }

    @RequestMapping(value = "/items/getAll", method = RequestMethod.GET)
    public ResponseEntity<Set<Item>> getAllItems()
    {
        Set<Item> itemlist = itemService.getAllItems();
        ResponseEntity<Set<Item>> response = new ResponseEntity<Set<Item>>(itemlist, HttpStatus.OK);
        return response;
    }


    @RequestMapping(value = "/items/getByName", method = RequestMethod.GET)
    public ResponseEntity<Set<Item>> getByName(@RequestParam String name)
    {
        Set<Item> item = itemService.getItemByNameContains(name);
        ResponseEntity<Set<Item>> response = new ResponseEntity<Set<Item>>(item, HttpStatus.OK);

        return response;
    }

    @RequestMapping(value = "/items/getByCat", method = RequestMethod.GET)
    public ResponseEntity<Set<Item>> getByCat(@RequestParam int cat)
    {
        ResponseEntity<Set<Item>> response;
        Set<Item> itemSet;


        itemSet = itemService.findAllByItemType(cat);
        if (itemSet.size() != 0)
        {
            response = new ResponseEntity<Set<Item>>(itemSet, HttpStatus.OK);

        } else
        {
            response = new ResponseEntity<Set<Item>>(itemSet, HttpStatus.NO_CONTENT);

        }
        return response;
    }

    @RequestMapping(value = "/items/updateItem", method = RequestMethod.GET)
    public ResponseEntity<Object> updateItem(@RequestParam long itemID, int cantidad)
    {
        ResponseEntity<Object> response;
        try
        {
            Item item = itemService.getItemById(itemID);

            try
            {
                itemService.updateStock(item, cantidad);
            }
            catch (InsufficientStockException e)
            {
//                log.error(e.getMessage());
                response = new ResponseEntity<Object>("No hay suficiente stock", HttpStatus.BAD_REQUEST);
            }

            item = itemService.getItemById(itemID);
            response = new ResponseEntity<Object>(item, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            response = new ResponseEntity<Object>("No encontramos ese carrito", HttpStatus.OK);
        }

        return null;
    }

    // end items

    //Warehouse

    @RequestMapping(value = "/getByEstado", method = RequestMethod.GET)
    public ResponseEntity<Object> getByEstado(@RequestParam int estado)
    {
        ResponseEntity<Object> response;
        Set<Cart> cartSet = new HashSet<>();

        try
        {
            response = new ResponseEntity<Object>(cartSet, HttpStatus.OK);

            cartSet.addAll(warehouseService.getAllCartsByState(estado));
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            response = new ResponseEntity<Object>(cartSet, HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
//            log.error("ERROR " + e.getMessage());
            response = new ResponseEntity<Object>(cartSet, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return response;
    }

    @RequestMapping(value = "/updateEstado", method = RequestMethod.GET)
    public ResponseEntity<Object> updateCartState(@RequestParam String userMOD, @RequestParam int state, @RequestParam long idCart)
    {
//        log.info("Start Update estado controller");
        ResponseEntity<Object> response;
        Cart cart = new Cart();
        try
        {
            cart = warehouseService.updateCart(idCart, state, userMOD);
            response = new ResponseEntity<Object>(cart, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            e.getMessage();

            response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @RequestMapping(value = "/updateEntry", method = RequestMethod.GET)
    ResponseEntity<Object> updateEntry(@RequestParam long cartEntryID,
                                       @RequestParam long cartId,
                                       @RequestParam String sn)
    {
        ResponseEntity<Object> response;
        try
        {
            Cart cart = warehouseService.getCart(cartId);
            CartEntry cartEntry = cartEntryDAO.findById(cartEntryID).orElse(null);
            cartEntry.setSerialnumber(sn);

            response = new ResponseEntity<Object>(cart, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
//            log.error("Error " + e.getMessage());
            response = new ResponseEntity<Object>("No encuentra el carro", HttpStatus.NO_CONTENT);
        }
        return response;
    }


    @RequestMapping(value = "/getByCliente", method = RequestMethod.GET)
    public ResponseEntity<Object> getByCliente(@RequestParam String dni)
    {
        ResponseEntity<Object> response;
        Set<Cart> cartSet = new HashSet<>();
        Cliente cliente = clienteService.getClienteByDNI(dni);
        try
        {
            response = new ResponseEntity<Object>(cartSet, HttpStatus.OK);

            cartSet.addAll(warehouseService.getAllCartsByClient(cliente));
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            response = new ResponseEntity<Object>(cartSet, HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
//            log.error("ERROR " + e.getMessage());
            response = new ResponseEntity<Object>(cartSet, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return response;
    }

    @RequestMapping(value = "/getCartEntriesByCart", method = RequestMethod.GET)
    ResponseEntity<Object> cartEntriesByCart(@RequestParam int cartId)
    {
        ResponseEntity<Object> responseEntity = null;
        Set<CartEntry> cartEntries = new HashSet<>();
        Cart cart = warehouseService.getCart(cartId);
        try
        {

            warehouseService.getAllCartEntriesByCart(cart).forEach(cartEntries::add);
            responseEntity = new ResponseEntity<Object>(cartEntries, HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity<Object>("No hay entradas", HttpStatus.OK);
//            log.info(e.getMessage());
        }

        return responseEntity;
    }

}
