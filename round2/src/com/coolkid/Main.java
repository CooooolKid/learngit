/******存储啤酒和果汁的list选用LinkedList的原因***********
 由于在炸鸡店管理系统中主要涉及到啤酒和果汁的增加和删除操作，
 而Linkedlist的增加和删除操作都是比较高效的，
 故选用Linkedlist来存储啤酒和果汁。
 ****************************************************/
package com.coolkid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        /**********西二炸鸡店***************************/
        //开局9999.99（bushi
        West2FriedChickenRestauran west2FriedChickenRestaurant=new West2FriedChickenRestauran(9999.99);

        //进货
        LocalDate now=LocalDate.now();
        Drinks orangeJuice=new Juice("orangeJuice",9.99,now.getYear(),now.getMonthValue(),now.getDayOfMonth(),2);
        Drinks budweiser=new Beer("Budweiser",9.99,now.getYear(),now.getMonthValue(),now.getDayOfMonth(),30,12);
        Drinks snowflake=new Beer("Snowflake",9.99,now.getYear(),now.getMonthValue(),now.getDayOfMonth(),30,8);
        try {
            west2FriedChickenRestaurant.batchStock(budweiser,1);
            west2FriedChickenRestaurant.batchStock(orangeJuice,10);
            west2FriedChickenRestaurant.batchStock(snowflake,10);
        } catch (OverdraftBalanceException e){
            System.out.println(e.getMessage());
        }

        //西二炸鸡店开张啦

        //介绍套餐
        SetMeal s1=new SetMeal("SetMealOne",66.66,"bigFriedChicken",orangeJuice);
        SetMeal s2=new SetMeal("setMealTwo",66.66,"bigFriedChicken",budweiser);
        SetMeal s3=new SetMeal("setMealThree",66.66,"bigFriedChicken",snowflake);
        System.out.println("现在西二炸鸡店在售以下三种套餐：");
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());

        //卖套餐
        west2FriedChickenRestaurant.sellSetMeal(s1);    //购买套餐一
        west2FriedChickenRestaurant.sellSetMeal(s2);    //购买套餐二
        west2FriedChickenRestaurant.sellSetMeal(s3);    //购买套餐三

        //套餐二对应的百威不够
        west2FriedChickenRestaurant.sellSetMeal(s2);

        //没那么多钱进那么多的百威
        try {
            west2FriedChickenRestaurant.batchStock(budweiser,10000);
        } catch (OverdraftBalanceException e){
            System.out.println(e.getMessage());
        }

        //最后输出炸鸡店的库存
        System.out.println(west2FriedChickenRestaurant.toString());

        /***************多线程***************************************/
        int blocks=10;   //将大数划分的块数，这边划分成10个，应该可以修改为100能跑的更快。。吧。。
        ArrayList<MyThread> myThread=new ArrayList<MyThread>(); //使用ArrayList存储便于随机访问
        long ans=0;
        Scanner scanner=new Scanner(System.in);
        int x=scanner.nextInt();    //应该含有的数字
        long num=scanner.nextInt(); //大数
        for (int i=0;i<blocks;i++){
            myThread.add(new MyThread(i,num,blocks,x));
            myThread.get(i).start();    //开启每个部分的线程
        }
        for (int i=0;i<blocks;i++){
            try {
                myThread.get(i).join(); //保证每个线程都结束再接下去运算
            }catch (InterruptedException e){}
        }
        for (int i=0;i<blocks;i++){     //将每块部分含有x的数的和加起来
            ans+=myThread.get(i).getThisPartAns();
        }
        //处理余数
        long left=num/10*10+1;
        for (;left<=num;left++){
            if (contain(left,x))
                ans+=left;
        }
        System.out.println(ans);

        /*********原方法测试（测试的时候用1e测试的，10e跑不动ivi）***********/
//        ans=0;
//        for (long i=1;i<=100000000;i++){
//            if (contain(i,x))
//                ans+=i;
//        }
//        System.out.println(ans);
    }

    private static boolean contain(long num,int x){
        return String.valueOf(num).contains(String.valueOf(x));
    }
}
