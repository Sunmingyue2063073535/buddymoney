package com.buddy.money.cash.india.loan.store.buddy.go;

import java.util.ArrayList;
import java.util.List;

public class BBB {
    public static void duplicateZeros(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int len = arr.length;
        for(int i = 0; i < len; i++){
            list.add(arr[i]);
            if(arr[i] == 0)
                list.add(0);
        }
        for(int i = 0; i < len; i++){
            arr[i] = list.get(i);
        }

    }
}
