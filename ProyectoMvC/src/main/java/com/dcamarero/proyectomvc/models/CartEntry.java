package com.dcamarero.proyectomvc.models;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true, exclude = "cart")
@Entity
public class CartEntry extends BaseEntity<CartEntry>
{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


/*    @JoinColumn(name = "cart_id")
    @ManyToOne
    @Cascade(value = {CascadeType.ALL})
    private Cart cart;*/


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private double price;

    @Column
    private int amount;

    @Column
    String serialnumber;

    @Override
    public int compareTo(BaseEntity<CartEntry> o)
    {
        return 0;
    }

    public CartEntry()
    {
        super();
    }

    public CartEntry(Cart cart, Item item, double price, int amount)
    {
        super();
        this.cart = cart;
        this.item = item;
        this.price = price;
        this.amount = amount;
    }

    public CartEntry(Cart cart, Item item, double price, int amount, String serialnumber)
    {
        super();
        this.cart = cart;
        this.item = item;
        this.price = price;
        this.amount = amount;
        this.serialnumber = serialnumber;
    }

    @Override
    public String toString()
    {
        return "CartEntry{" +
                "cartDTO=" + cart +
                ", item=" + item +
                ", price=" + price +
                ", amount=" + amount +
                ", serialnumber='" + serialnumber + '\'' +
                "} " + super.toString();
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
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

    public String getSerialnumber()
    {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber)
    {
        this.serialnumber = serialnumber;
    }
}
