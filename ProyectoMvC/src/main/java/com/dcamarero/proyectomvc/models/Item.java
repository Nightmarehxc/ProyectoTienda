package com.dcamarero.proyectomvc.models;

import com.dcamarero.proyectomvc.models.enums.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class Item extends BaseEntity<Item>
{

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(nullable = false)
    private double price;

    @Column()
    private int amount;

    @Column(nullable = false)
    private LocalDateTime lastMod;

    @Column(nullable = false)
    private String lastUserModification;


    public Item()
    {
        super();
    }

    public Item(String name, ItemType itemType, double price, int amount, LocalDateTime lastMod, String lastUserModification)
    {
        this.name = name;
        this.itemType = itemType;
        this.price = price;
        this.amount = amount;
        this.lastMod = lastMod;
        this.lastUserModification = lastUserModification;
    }

    @Override
    public int compareTo(BaseEntity<Item> o)
    {
        return 0;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ItemType getItemType()
    {
        return itemType;
    }

    public void setItemType(ItemType itemType)
    {
        this.itemType = itemType;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public LocalDateTime getLastMod()
    {
        return lastMod;
    }

    public void setLastMod(LocalDateTime lastMod)
    {
        this.lastMod = lastMod;
    }

    public String getLastUserModification()
    {
        return lastUserModification;
    }

    public void setLastUserModification(String lastUserModification)
    {
        this.lastUserModification = lastUserModification;
    }
}
