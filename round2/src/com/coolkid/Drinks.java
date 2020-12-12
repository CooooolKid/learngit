package com.coolkid;
import java.time.*;

public abstract class Drinks {
    protected String name;
    protected double cost;  //成本
    protected LocalDate dateOfManufacture;  //生产日期
    protected int life; //保质期

    public Drinks(String n,double c,int year,int month,int day,int l){
        name=n;
        cost=c;
        dateOfManufacture=LocalDate.of(year,month,day);
        life=l;
    }

    boolean isOutOfDate(){
        LocalDate today=LocalDate.now();
        return today.isAfter(dateOfManufacture.plusDays(life));
    }

    public double getCost(){
        return cost;
    }

    public abstract String toString();
    public abstract String getName();
}
