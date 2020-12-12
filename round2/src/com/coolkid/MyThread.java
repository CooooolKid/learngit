package com.coolkid;

public class MyThread extends Thread {
    private int blocks;
    private int part;
    private long num;
    private long thisPartAns;
    private int target;

    public MyThread(int p,long n,int b,int x){
        blocks=b;
        part=p;
        num=n;
        thisPartAns=0;
        target=x;
    }

    public void run(){
        long initial=num/blocks*part+1;
        long end=num/blocks*(part+1);
        for (;initial<=end;initial++){
            if (contain(initial))
                thisPartAns+=initial;
        }
    }

    public long getThisPartAns(){
        return thisPartAns;
    }

    private boolean contain(long x){
        return String.valueOf(x).contains(String.valueOf(target));
    }
}
