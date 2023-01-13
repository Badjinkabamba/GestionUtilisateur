package com.sid.gestionUtilisateurs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author A734647
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message){
        super(message);
    }
}