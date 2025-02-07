package com.scaler.userservice_rbac.exceptions;

public class UserAlreadyExistInSystem extends Exception{
    public UserAlreadyExistInSystem(String message){
        super(message);
    }
}
