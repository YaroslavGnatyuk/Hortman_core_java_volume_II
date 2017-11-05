package gnatyuk.java.core.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Boss ", "Man ", "Love ", "Life ", "Boss ");

        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }

        Stream<Stream<String>> streamStream = words.stream().map(e->getLetters(e));
        Stream<String> stringStream = words.stream().flatMap(e->getLetters(e));
        stringStream.forEach(System.out::print);
        words.stream().distinct().forEach(System.out::print);
        Function<String, String> func = (String e)->{
            if(e.contains("Li")){
                return e;
            }
            return "";
        };
        Map<String, List<String>> w = words.stream().collect(Collectors.groupingBy(func));

//        w.keySet().stream().forEach(System.out::println);
//        w.values().stream().forEach(System.out::println);
    }

    private static Stream<String> getLetters(String word){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            res.add(word.substring(i, i+1));
        }
        return res.stream();
    }
}
