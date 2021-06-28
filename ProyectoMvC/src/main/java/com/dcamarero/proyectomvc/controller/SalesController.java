package com.dcamarero.proyectomvc.controller;


import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.services.ItemService;
import com.dcamarero.proyectomvc.services.SalesService;
import com.dcamarero.proyectomvc.services.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/shop")
public class SalesController
{
    @Autowired
    ItemService itemService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    SalesService salesService;

    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public ResponseEntity<Object> addItem(@RequestParam long itemId,
                                          @RequestParam String clientID,
                                          @RequestParam int cantidad)
    {
//        String clientID = SessionContext.get(Session.IdClient);// todo añadir session Context para recoger la ID mediante un JWT acces token
        ResponseEntity<Object> response = null;
        try
        {

            salesService.updateCart(clientID, cantidad, itemId);

        }
        catch (Exception e)
        {
//            log.info("e.getMessage()");
        }
        return response;
    }


}
