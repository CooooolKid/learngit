package com.coolkid;

public class OverdraftBalanceException extends RuntimeException{
    public OverdraftBalanceException(){
        super();
    }

    public OverdraftBalanceException(String message){
        super(message);
    }
}
