package com.supply_chain_easy.supply_chain_base_operations.exceptions;

public class RetryAttemptsFailedException extends RuntimeException{
    public RetryAttemptsFailedException(String message){
        super(message);
    }
}
