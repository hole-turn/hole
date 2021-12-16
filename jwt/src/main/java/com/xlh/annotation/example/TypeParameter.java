package com.xlh.annotation.example;

import com.xlh.annotation.MyExample;

import java.util.ArrayList;
import java.util.List;


public class TypeParameter<@MyExample T> {

    public <@MyExample U> T foo(T t) {
        return null;
    }

    public static @MyExample class TypeParameterClass<@MyExample T> extends @MyExample Object {
        public void foo(@MyExample T t) throws @MyExample Exception {

        }
    }

    @SuppressWarnings({"rawtypes", "unused", "resource"})
    public static void main(String[] args) throws Exception {
        TypeParameterClass<@MyExample String> typeParameterClass = new @MyExample TypeParameterClass<>();
        typeParameterClass.foo("");
        List<@MyExample Comparable> list = new ArrayList<>();
        List<? extends Comparable> list2 = new ArrayList<@MyExample Comparable>();
        @MyExample String text = (@MyExample String) new Object();
        java.util.@MyExample Scanner scanner = new java.util.@MyExample Scanner(System.in);

    }

}
