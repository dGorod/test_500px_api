package com.dgorod.example.exception;

/**
 * Indicates that specified component was not initialized before usage.
 *
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public class NotInitializedException extends RuntimeException {
    public NotInitializedException(Class componentClass) {
        super(componentClass.getSimpleName() + " component is not initialized! Please, call method init() first.");
    }
}