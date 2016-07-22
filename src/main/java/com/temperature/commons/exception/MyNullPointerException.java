package com.temperature.commons.exception;

/**
 * The {@code MyNullPointerException} class extends the {@code Exception} class
 * because we want {@code MyNullPointerException} to be a checked {@code Exception}
 * rather than a {@code RuntimeException}. This forces the developer to surround any
 * method call that throws a {@code MyNullPointerException} in a {@code try} {@code catch} block.
 *
 * @author Jabari Dash
 *
 * Created by Jabari on 07/17/2016.
 */
public class MyNullPointerException extends Exception {
    public MyNullPointerException(String message) {
        super(message);
    }
}
