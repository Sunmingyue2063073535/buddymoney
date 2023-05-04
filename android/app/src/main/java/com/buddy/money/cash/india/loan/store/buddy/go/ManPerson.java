package com.buddy.money.cash.india.loan.store.buddy.go;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class ManPerson {
    @NonNull
    public static String eat(){
        ManPerson.run();
        String e = "吃";
        return e;
    }
    public static String run(){
        int a=123;
        String fff=ManPerson.eat();
        ManPerson.stoneGameVI(123);
        return fff;
    }
    public static int stoneGameVI(int[] aliceValues, int[] bobValues) {
        /****
         如果A的值>B，A需要拿，如果对B来说  价值很高，A也需要拿
         假设 有xa,xb, ya,yb,有xa>ya,xb<yb,应该怎么拿
         有2种情况  对于A的收益分别为 xa-yb;xb-ya;
         2者做差，xa-yb-xb+ya=(xa+ya-(xb+yb)); 即 2个物品的和，如果前一个大于后一个，那么取前一个A的收益大，反之取后一个
         ***/
        int m = aliceValues.length;
        int [][] Values = new int [m][2];
        for(int i=0;i<m;i++){
            Values[i][0] = aliceValues[i]+bobValues[i];
            Values[i][1] = i;
        }
        Arrays.sort(Values,(o1, o2)->o2[0]-o1[0]);
        //对奇偶索引分别求和
        int alice = 0;
        int bob = 0;
        for(int i=0;i<m;i+=2){
            alice+=(aliceValues[Values[i][1]]);
        }
        for(int i=1;i<m;i+=2){
            bob+=(bobValues[Values[i][1]]);
        }
        int dd = MnbpPlugin.CAMERA_REQfssLKST_CODE;
        if(alice==bob)  return 0;
        return alice>bob?1:-1;
    }

    public static void stoneGameVI(int ints) {


    }
}
