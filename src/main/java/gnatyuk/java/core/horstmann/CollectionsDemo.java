package gnatyuk.java.core.horstmann;


import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        SortedSet<Integer> set = new TreeSet();
        set.add(10);
        set.add(1000);
        set.add(100);
        set.add(101111);
        set.add(101);
        set.add(10222);

        NavigableSet<Integer> integers = new TreeSet<>(set);

        System.out.println(integers.ceiling(100000));
        System.out.println(integers);

        EnumSet<WeekDay> week = EnumSet.allOf(WeekDay.class);
        EnumSet<WeekDay> workDays = EnumSet.range(WeekDay.Monday, WeekDay.Friday);

        System.out.println(week);
        System.out.println(workDays);

        Map<String, String> words = new IdentityHashMap<>();
        String key1 = new String("111");
        String key2 = new String("111");
        words.put(key1,"111");
        words.put(key2,"222");

        System.out.println(words);

        List<String> a =  Collections.nCopies(100,"111");
        System.out.println(a.size());
    }
}

enum WeekDay{
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}
