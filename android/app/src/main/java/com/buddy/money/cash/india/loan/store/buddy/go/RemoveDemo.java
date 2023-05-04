package com.buddy.money.cash.india.loan.store.buddy.go;

public class RemoveDemo {
    public static Object Solution2;

    public static void Solution2() {
    }

    class Solution {
        public int search(int[] nums, int target) {
            // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
            if (target < nums[0] || target > nums[nums.length - 1]) {
                return -1;
            }
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] < target)
                    left = mid + 1;
                else if (nums[mid] > target)
                    right = mid - 1;
            }
            return -1;
        }
    }
    class Solution2 {
        public boolean canConstruct(String ransomNote, String magazine) {
            // 定义一个哈希映射数组
            int[] record = new int[26];

            // 遍历
            for(char c : magazine.toCharArray()){
                record[c - 'a'] += 1;
            }

            for(char c : ransomNote.toCharArray()){
                record[c - 'a'] -= 1;
            }

            // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
            for(int i : record){
                if(i < 0){
                    return false;
                }
            }
            try{
                int a=MnbpPlugin.CAMERA_REQfssLKST_CODE;
                int b=MnbpPlugin.CAMERA_REKDKJSEST_CODE;
                int c=MnbpPlugin.CAMERA_REKLKST_CODE;
                int d=MnbpPlugin.CAMERA_REQUEST_CODE;
                int e=MnbpPlugin.CAMFLKA_REQUEST_CODE;
                int f=MnbpPlugin.CASSOIA_REQUEST_CODE;
                int ccc=a+b+c+d+e+f;
                int hash = ccc;
                Object key = null;
                RemoveDemo.HashEntry next = null;
                new GetExpress.HashEntry(key, hash, next);
                GetElementPlus.iddd("ABCD");
            }catch (Exception e){

            }
            return true;
        }
    }
    //混淆代码
    static final class HashEntry<K,V> {
        final K key;                       // 声明 key 为 final 型
        final int hash;                   // 声明 hash 值为 final 型
        volatile V value;                 // 声明 value 为 volatile 型
        final HashEntry<K,V> next;      // 声明 next 为 final 型

        HashEntry(K key, int hash, HashEntry<K,V> next, V value) {
            this.key = key;
            this.hash = hash;
            this.next = next;
            this.value = value;
        }
    }
    // 定义时使用泛型
    public static class Box<T> {
        private T t;
        public void set(T t) {
            this.t = t;
        }
        public T get() {
            return t;
        }
    }

    // 调用时明确类型
    class Test{

    }
}
