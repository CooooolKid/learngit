package com.coolkid;
import java.time.LocalDate;
import java.util.*;

public class West2FriedChickenRestauran implements FriedChickenRestaurant {
    private double balance;
    private LinkedList<Beer> beers;
    private LinkedList<Juice> juices;
    private static ArrayList<SetMeal> setMeals=new ArrayList<>();

    public West2FriedChickenRestauran(double b){
        beers=new LinkedList<>();
        juices=new LinkedList<>();
        balance=b;
    }

    //静态代码块
    static {
        LocalDate now=LocalDate.now();
        Drinks orangeJuice=new Juice("orangeJuice",9.99,now.getYear(),now.getMonthValue(),now.getDayOfMonth(),2);
        Drinks budweiser=new Beer("Budweiser",9.99,now.getYear(),now.getMonthValue(),now.getDayOfMonth(),30,12);
        Drinks snowflake=new Beer("Snowflake",9.99,now.getYear(),now.getMonthValue(),now.getDayOfMonth(),30,8);
        setMeals.add(new SetMeal("setMealOne",66.66,"bigFriedChicken",orangeJuice));
        setMeals.add(new SetMeal("setMealTwo",66.66,"bigFriedChicken",budweiser));
        setMeals.add(new SetMeal("setMealThree",66.66,"bigFriedChicken",snowflake));
    }

    //use方法，主要使用到了indexOf方法
    private void use(Beer b){
        //清理过期饮料
        clearOutOfDateDrinks();
        int index=beers.indexOf(b);
        if (index==-1){
            //抛出啤酒库存不足的异常
            throw new IngredientSortOutException(b.getName()+"卖光了哦！");
        } else {
            beers.remove(index);
        }
    }

    private void use(Juice j){
        //清理过期饮料
        clearOutOfDateDrinks();
        int index=juices.indexOf(j);
        if (index==-1){
            //抛出果汁库存不足的异常
            throw new IngredientSortOutException(j.getName()+"卖光了哦！");
        } else {
            juices.remove(index);
        }
    }

    public void sellSetMeal(SetMeal aSetMeal){
        try{
            if (aSetMeal.getDrink() instanceof Beer){
                use((Beer)aSetMeal.getDrink());
                balance+=aSetMeal.getPrice();   //卖出余额增加
                System.out.println(aSetMeal.getDrink().name);
            }
            else if (aSetMeal.getDrink() instanceof Juice) {
                use((Juice)aSetMeal.getDrink());
                balance+=aSetMeal.getPrice();
                System.out.println(aSetMeal.getDrink().name);
            }
        } catch (IngredientSortOutException e){
            System.out.println(e.getMessage());
        }
    }

    public void batchStock(Drinks aDrink,int num){
        if (aDrink.getCost()*num>balance){
            //抛出余额不足以进那么多货的异常
            throw new OverdraftBalanceException("没钱啦！还差"+(aDrink.getCost()*num-balance)+"进"+num+"瓶"+aDrink.getName());
        }
        if (aDrink instanceof Beer){
            for (int i=0;i<num;i++){
                beers.add((Beer) aDrink);
                balance-=aDrink.getCost();
            }
        } else {
            for (int i=0;i<num;i++){
                juices.add((Juice)aDrink);
                balance-=aDrink.getCost();
            }
        }
    }

    //清理过期的饮料
    private void clearOutOfDateDrinks() {

        //清理过期啤酒
        Iterator<Beer> iterB = beers.iterator();
        while (iterB.hasNext()) {
            Beer b = iterB.next();
            if (b.isOutOfDate()) {
                iterB.remove();
            }
        }

        //清理过期果汁
        Iterator<Juice> iterJ = juices.iterator();
        while (iterJ.hasNext()) {
            Juice j = iterJ.next();
            if (j.isOutOfDate()) {
                iterJ.remove();
            }
        }
    }

    public String toString(){
        return "余额："+balance+"元，啤酒还有："+beers.size()+"瓶，果汁还有："+juices.size()+"瓶";
    }
}
