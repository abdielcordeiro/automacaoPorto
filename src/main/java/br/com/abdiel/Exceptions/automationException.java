package br.com.abdiel.Exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class automationException extends RuntimeException {

    public automationException(String msg){
        super(msg);
    }
}
