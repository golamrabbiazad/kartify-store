package com.golamrabbiazad.primaxstorebackend.exception;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String message) {
        System.out.println(message);
    }
}
