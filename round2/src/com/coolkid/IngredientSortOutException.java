package com.coolkid;

public class IngredientSortOutException extends RuntimeException{
    public IngredientSortOutException(){
        super();
    }

    public IngredientSortOutException(String message){
        super(message);
    }
}
