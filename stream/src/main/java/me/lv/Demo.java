package me.lv;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * @author zerozero
 * @date 2022/7/27
 */
public class Demo {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int size = 1_000_000;
        List<Integer> list = Lists.newArrayListWithCapacity(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100));
        }
        long start = System.currentTimeMillis();
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        list.stream().parallel().reduce(Integer::sum).ifPresent(System.out::println);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
