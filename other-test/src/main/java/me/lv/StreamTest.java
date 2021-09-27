package me.lv;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author plume
 * @date 2021/8/19 14:22
 */
public class StreamTest {
    public static void main(String[] args) {
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
