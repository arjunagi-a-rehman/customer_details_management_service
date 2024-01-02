package com.arjunagi.customer_details_management_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerDetailsAlreadyExistsException extends RuntimeException{
    public CustomerDetailsAlreadyExistsException(String message){
        super(message);
    }
}


