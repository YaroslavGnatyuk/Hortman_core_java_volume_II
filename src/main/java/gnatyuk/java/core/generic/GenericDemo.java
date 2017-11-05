package gnatyuk.java.core.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by yroslav on 11/15/16.
 */
public class GenericDemo<T> {
    public void test(Collection<T> collection){
        collection.forEach(System.out::println);
    }

    public void test(List<Integer> collectionInteger){
        collectionInteger.forEach(System.out::println);
    }

    public static void main(String[] args) {
        GenericDemo<Object> genericDemo = new GenericDemo<Object>();
        List<String> list = Arrays.asList("value");

//        genericDemo.test(list);
    }
}

