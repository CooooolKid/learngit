package com.coolkid;

import java.util.Set;

public class SetMeal {
    private String name;
    private double price;
    private String friedChickenName;
    private Drinks drink;

    public SetMeal(String n,double p,String fCN,Drinks d){
        name=n;
        price=p;
        friedChickenName=fCN;
        drink=d;
    }

    public String toString(){
        return name+":售价"+price+"元，含有"+friedChickenName+"和"+drink.toString();
    }

    public Drinks getDrink(){
        return drink;
    }

    public double getPrice(){
        return price;
    }
}
