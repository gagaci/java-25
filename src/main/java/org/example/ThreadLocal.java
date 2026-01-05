package org.example;

public class ThreadLocal {
    static void main() {

        java.lang.ThreadLocal<Integer> threadLocal = new java.lang.ThreadLocal<>();

        threadLocal.set(1);

        Integer result = threadLocal.get();


    }
}
