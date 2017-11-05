package gnatyuk.java.core.guava;

import com.google.common.base.Optional;

/**
 * Created by yroslav on 11/11/16.
 */
public class GuavaDemoOptional {
    public static void main(String args[]) {
        GuavaDemoOptional guavaTester = new GuavaDemoOptional();

        Integer value1 =  null;
//        Preconditions.checkArgument(value1 != null, "We have here NPE!!!");

        Integer value2 =  new Integer(10);

        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);

        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);

        System.out.println(guavaTester.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }
}
