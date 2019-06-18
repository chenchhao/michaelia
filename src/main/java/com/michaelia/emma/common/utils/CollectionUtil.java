package com.michaelia.emma.common.utils;

import java.util.*;

/**
 * 集合的交并补运算
 *
 * @author wuyong
 */
public class CollectionUtil {

    /**
     * 交集
     *
     * @param c1
     * @param c2
     * @param <T>
     * @return
     */
    public static <T> Collection<T> inter(Collection<T> c1, Collection<T> c2) {
//        Assert.notEmpty(c1, "parameter must not be empty");
//        Assert.notEmpty(c2, "parameter must not be empty");
        LinkedHashSet<T> c = new LinkedHashSet<>();
        c.addAll(c1);
        c.retainAll(c2);
        return c;
    }

    /**
     * 并集
     *
     * @param c1
     * @param c2
     * @return
     */
    public static <T> Collection<T> union(Collection<T> c1, Collection<T> c2) {
//        Assert.notEmpty(c1, "parameter must not be empty");
//        Assert.notEmpty(c2, "parameter must not be empty");
        LinkedHashSet<T> c = new LinkedHashSet<>();
        c.addAll(c1);
        c.addAll(c2);
        return c;
    }

    /**
     * 差集
     *
     * @param c1
     * @param c2
     * @return
     */
    public static <T> Collection<T> diff(Collection<T> c1, Collection<T> c2) {
//        Assert.notEmpty(c1, "parameter must not be empty");
//        Assert.notEmpty(c2, "parameter must not be empty");
        LinkedHashSet<T> c = new LinkedHashSet<>();
        c.addAll(c1);
        c.removeAll(c2);
        return c;
    }

    public static void main(String[] args) {
        Set<String> c1 = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> c2 = new HashSet<>(Arrays.asList("c", "d", "e"));
        System.out.println(inter(c1, c2));
        System.out.println(union(c1, c2));
        System.out.println(diff(c1, c2));
    }

}
