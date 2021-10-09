package com.nps.devassessment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NpsException extends  Exception{
    public NpsException (String message){
        super(message);
    }

    public static  String NotFoundException (Long id){
        return "Entity with id " + id +" not found." ;
    }

    public static String EntityAlreadyExists(){
        return "Entity with properties already exists.";
    }

}
