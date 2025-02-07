package com.scaler.userservice_rbac.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
