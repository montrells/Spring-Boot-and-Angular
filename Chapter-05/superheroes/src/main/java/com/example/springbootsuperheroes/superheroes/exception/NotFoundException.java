package com.example.springbootsuperheroes.superheroes.exception;


public class NotFoundException extends RuntimeException {// extending the runtime exception
//Its called only when the id is not present in the database.
    public NotFoundException(String message) {//sends the message using the RuntimeException
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
