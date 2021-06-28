package com.dcamarero.proyectomvc.services.impl;

import com.dcamarero.proyectomvc.dao.ItemDao;
import com.dcamarero.proyectomvc.exception.InsufficientStockException;
import com.dcamarero.proyectomvc.models.Item;
import com.dcamarero.proyectomvc.models.enums.ItemType;
import com.dcamarero.proyectomvc.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The type Item service.
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService
{
    private final ItemDao itemDao;

    /**
     * Instantiates a new Item service.
     *
     * @param itemDao the item dao
     */
    @Autowired
    public ItemServiceImpl(ItemDao itemDao)
    {
        this.itemDao = itemDao;
    }


    @Override
    public void save(Item item)
    {
        itemDao.save(item);
    }

    @Override
    public Item getItemById(Long id)
    {
        final Optional<Item> response = itemDao.findById(id);
        return response.orElse(null);
    }

    @Override
    public Item getItemByName(String name)
    {
        return null;
    }

    @Override
    public Set<Item> getAllItems()
    {
        final Set<Item> result = new HashSet<>();
        itemDao.findAll().forEach(result::add);
        return result;
    }

    @Override
    public void deleteById(long id)
    {
        itemDao.deleteById(id);
    }

    @Override
    public Set<Item> findAllByItemType(int itemTypeInt)
    {
        final Set<Item> result = new HashSet<>();
        try
        {

            switch (itemTypeInt)
            {
                case 0:
                    itemDao.findAllByItemType(ItemType.COMPONENTES).forEach(result::add);
                case 1:
                    itemDao.findAllByItemType(ItemType.MOVILES).forEach(result::add);
                case 2:
                    itemDao.findAllByItemType(ItemType.TABLETAS).forEach(result::add);
                case 3:
                    itemDao.findAllByItemType(ItemType.MOVILES).forEach(result::add);
            }


        }
        catch (EntityNotFoundException e)
        {
//            log.info("" + e.getMessage());
        }
        return result;

    }

    @Override
    public boolean checkAvailabilityStock(final Item item, final int modification)
    {
        final double updateStock = item.getAmount() - modification;
        return updateStock >= 0;
    }

    @Override
    public void updateStock(final Item item, final int modification) throws InsufficientStockException
    {
        final int updateStock = item.getAmount() - modification;
        if (updateStock >= 0)
        {
            item.setAmount(updateStock);
            itemDao.save(item);
            return;
        }

        throw new InsufficientStockException("Item " + item.getName() + " has not sufficient stock to serve");
    }

    @Override
    public Set<Item> getItemByNameContains(String name)
    {
        final Set<Item> result = new HashSet<>();
        itemDao.findAllByNameContains(name).forEach(result::add);
        return result;
    }
}
