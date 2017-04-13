package demo.java8.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by dongsj on 2017/3/31.
 */
public class StreamTest {

    List<String> things = new ArrayList<String>(){{
        add("Eating-Lunch");
        add("Eating-Dinner");
        add("Eating-Breakfast");
        add("Working");
        add("Sleeping");
        add("Drinking");
    }};

    @Test
    public void test() {
        long count = things.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {

                return s.startsWith("Eating");
            }
        }).count();
        System.out.println(count);

        count = things.stream().filter(event -> event.startsWith("Eating")).count();
        System.out.println(count);
    }

    @Test
    public void test1() {
        List<String> eatingThings = things.stream().filter(event -> event.startsWith("Eating")).collect(Collectors.toList());

        for (String eatingThing : eatingThings) {
            System.out.println(eatingThing);
        }
    }

}
