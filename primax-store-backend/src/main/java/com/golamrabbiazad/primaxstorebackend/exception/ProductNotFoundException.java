package com.golamrabbiazad.primaxstorebackend.exception;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String message) {
        System.out.println(message);
    }
}
