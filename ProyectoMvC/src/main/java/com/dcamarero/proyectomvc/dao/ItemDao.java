package com.dcamarero.proyectomvc.dao;

import com.dcamarero.proyectomvc.models.Item;
import com.dcamarero.proyectomvc.models.enums.ItemType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The interface Item dao.
 */
public interface ItemDao extends CrudRepository<Item, Long>
{
    @Override
    Optional<Item> findById(Long aLong);

    /**
     * Find by name optional.
     *
     * @param aName the a name
     * @return the optional
     */
    Optional<Item> findByName(String aName);

    @Override
    Iterable<Item> findAllById(Iterable<Long> iterable);

    /**
     * Find all by item type iterable.
     *
     * @param itemType the item type
     * @return the iterable
     */
    Iterable<Item> findAllByItemType(ItemType itemType);

    @Override
    void delete(Item item);

    @Override
    <S extends Item> S save(S s);

    @Override
    <S extends Item> Iterable<S> saveAll(Iterable<S> iterable);


    Iterable<Item> findAllByNameContains(String name);
}
