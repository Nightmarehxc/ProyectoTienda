package com.dcamarero.proyectomvc.services;

import com.dcamarero.proyectomvc.exception.InsufficientStockException;
import com.dcamarero.proyectomvc.models.Item;

import java.util.Set;

/**
 * The interface Item service.
 */
public interface ItemService
{
    /**
     * Save.
     *
     * @param item the item
     */
    void save(Item item);

    /**
     * Gets item by id.
     *
     * @param id the id
     * @return the item by id
     */
    Item getItemById(Long id);

    /**
     * Gets item by name.
     *
     * @param name the item
     * @return the item by name
     */
    Item getItemByName(String name);

    /**
     * Gets all Items.
     *
     * @return the all items
     */
    Set<Item> getAllItems();

    /**
     * Delete item by id.
     *
     * @param id the id
     */
    void deleteById(long id);

    /**
     * Find all by item type set.
     *
     * @param itemType the item type
     * @return set set
     */
    Set<Item> findAllByItemType(int itemType);

    /**
     * Check availability stock from a item.
     * <code>true</code> if there are stock to serve, <code>false</code> if not
     *
     * @param item         the item
     * @param modification the modification
     * @return the boolean
     */
    boolean checkAvailabilityStock(final Item item, final int modification);

    /**
     * Update stock.
     *
     * @param item       the item
     * @param cartWeight the cart weight
     * @throws InsufficientStockException the insufficient stock exception
     */
    void updateStock(Item item, int cartWeight) throws InsufficientStockException;

    Set<Item> getItemByNameContains(String name);
}
