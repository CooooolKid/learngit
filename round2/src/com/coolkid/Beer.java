package com.coolkid;

public class Beer extends Drinks {
    private float vol;
    public Beer(String n,double c,int year,int month,int day,int l,float v){
        super(n,c,year,month,day,l);
        vol=v;
    }

    public String toString(){
        int year=dateOfManufacture.getYear();
        int month=dateOfManufacture.getMonthValue();
        int day=dateOfManufacture.getDayOfMonth();
        return name+"(酒精度数："+vol+",生产日期："+year+"."+month+"."+day+",保质期："+life+"天。)";
    }

    public String getName(){
        return name;
    }
}
