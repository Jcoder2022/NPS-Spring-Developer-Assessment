package com.nps.devassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NpsException extends  Exception{
    public NpsException (String message){
        super(message);

    }
}
