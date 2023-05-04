package com.buddy.money.cash.india.loan.store.buddy.go;

public class GetExpress {
    public static boolean canConstruct(String ransomNote, String magazine) {
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

        return true;
    }

    public static boolean canConstruct() {
        int[] record = new int[26];

        // 遍历
        String magazine = null;
        for(char c : magazine.toCharArray()){
            record[c - 'a'] += 1;
        }

        String ransomNote = null;
        for(char c : ransomNote.toCharArray()){
            record[c - 'a'] -= 1;
        }

        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for(int i : record){
            if(i < 0){
                return false;
            }
        }

        return true;
    }
    public static final class HashEntry<K,V> {
        final K key;                       // 声明 key 为 final 型
        final int hash;                   // 声明 hash 值为 final 型
        volatile V value;                 // 声明 value 为 volatile 型
        final RemoveDemo.HashEntry<K,V> next;      // 声明 next 为 final 型

        HashEntry(K key, int hash, RemoveDemo.HashEntry<K,V> next, V value) {
            this.key = key;
            this.hash = hash;
            this.next = next;
            this.value = value;
        }

        public HashEntry(K key, int hash, RemoveDemo.HashEntry<K, V> next) {
            this.key = key;
            this.hash = hash;

            this.next = next;
        }
    }
}
