package com.dcamarero.proyectomvc.application;

import com.dcamarero.proyectomvc.dao.*;
import com.dcamarero.proyectomvc.models.*;
import com.dcamarero.proyectomvc.models.enums.EstadoType;
import com.dcamarero.proyectomvc.models.enums.ItemType;
import com.dcamarero.proyectomvc.models.enums.UserType;
import com.dcamarero.proyectomvc.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class TiendaManager implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    ItemService itemService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    ItemDao itemDao;

    @Autowired
    WarehouseService wareHouseService;

    @Autowired
    SalesService salesService;

    @Autowired
    CartDAO cartDAO;

    @Autowired
    CartEntryDAO cartEntryDAO;


    public TiendaManager()
    {
    }


    @Override
    public void run(String... args) throws Exception
    {

        final User u1 = new User("ADMIN", "123@email.com", "12345", UserType.ADMIN, "12345678A", "reus1", "+3412345678", LocalDateTime.now(), true);
        final User u2 = new User("USER", "email@email.com", "12345", UserType.REGULAR, "87654321Z", "reus2", "+3412345679", LocalDateTime.now(), true);
        final User u3 = new User("SHOP", "email@email.com", "12345", UserType.REGULAR, "000000000", "reus2", "+1111111111", LocalDateTime.now(), true);

        userService.saveNewUser(u1);
        userService.saveNewUser(u2);
        userService.saveNewUser(u3);


        final Item i1 = new Item("Led 01", ItemType.COMPONENTES, 5, 200, LocalDateTime.now(), u1.getDni());
        final Item i2 = new Item("Led 02", ItemType.COMPONENTES, 1, 125, LocalDateTime.now(), u1.getDni());
        final Item i3 = new Item("Led 03", ItemType.COMPONENTES, 1, 70, LocalDateTime.now(), u1.getDni());


        final Item i4 = new Item("Lenovo Experience", ItemType.TABLETAS, 100, 70, LocalDateTime.now(), u1.getDni());


        itemService.save(i1);
        itemService.save(i2);
        itemService.save(i3);
        itemService.save(i4);

        final Cliente c1 = new Cliente("50229090-B", "alf", "Alien", "1", "616551122", "12345", "testuser", LocalDateTime.now(), LocalDateTime.now(), null, true);
//        final ClienteDTO c1 = new ClienteDTO("50229090-B", "alf", "Alien", "1", "616551122", "12345", "testuser", LocalDateTime.now(), LocalDateTime.now(), new HashSet<CartDTO>(), true);

        clienteService.saveNewUser(c1);


//
//
        Cart cart = new Cart();
        cart.setSellDate(LocalDateTime.now());
        cart.setCliente(c1);
        cart.setEstado(EstadoType.EDITANDO);
        cart.setSeller(u3);
        cartDAO.save(cart);

        CartEntry cartEntry = new CartEntry(cart, i1, i1.getPrice(), 3);
        cartEntryDAO.save(cartEntry);
//        log.info("update cart " + cartEntry.getItem().getPrice()*cartEntry.getAmount());
        cart.setTotal(cartEntry.getItem().getPrice() * cartEntry.getAmount());
        cartDAO.save(cart);
//        log.info(String.valueOf(c1.getId()));
//        log.info(String.valueOf(cart.getId()));

//        cartDAO.findAllByClienteDTOAndEstado(c1,EstadoType.EDITANDO);
        //cartDTO.getCartEntries().add(cartEntry);
//        cartDAO.save(cartDTO);

//
//        log.info(cartDAO.findAll().toString());
//


//        log.info(c1.toString());
//        log.info(cart.toString());


//
//        clientDAO.save(c1);


    }
}

