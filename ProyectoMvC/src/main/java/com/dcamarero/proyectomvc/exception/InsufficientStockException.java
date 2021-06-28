package com.dcamarero.proyectomvc.exception;

/**
 * The type Insufficient stock exception.
 */
public class InsufficientStockException extends Exception
{
    /**
     * Instantiates a new Insufficient stock exception.
     *
     * @param message the message
     */
    public InsufficientStockException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new Insufficient stock exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public InsufficientStockException(String message, Throwable cause)
    {
        super(message, cause);
    }
}