package com.golamrabbiazad.primaxstorebackend.exception;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(String message) {
        System.out.println(message);
    }
}
