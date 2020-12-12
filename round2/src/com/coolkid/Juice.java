package com.coolkid;

public class Juice extends Drinks {
    public Juice(String n,double c,int year,int month,int day,int l){
        super(n,c,year,month,day,l);
    }

    public String toString(){
        int year=dateOfManufacture.getYear();
        int month=dateOfManufacture.getMonthValue();
        int day=dateOfManufacture.getDayOfMonth();
        return name+"(生产日期："+year+"."+month+"."+day+",保质期："+life+"天。)";
    }

    public String getName(){
        return name;
    }
}
