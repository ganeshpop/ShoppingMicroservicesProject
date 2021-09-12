package com.order.exception;


public class ProductNotFoundInInventoryException extends RuntimeException {
    public ProductNotFoundInInventoryException(String message) {
        super(message);
    }
}
