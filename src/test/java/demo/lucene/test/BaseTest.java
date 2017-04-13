package demo.lucene.test;

import org.junit.Test;

import java.util.Date;

/**
 * Created by dongsj on 17/3/5.
 */
public class BaseTest {

    public static void main(String[] args) {
        System.out.println(new Date());
    }


    @Test
    public void test1() {
        Integer a1 = 100;
        Integer b1 = 100;
        System.out.println(a1==b1);

        Integer a2 = 1000;
        Integer b2 = 1000;
        System.out.println(a2==b2);

        Integer c1 = 100;
        System.out.println(c1==100);

        Integer c2 = 1000;
        System.out.println(c2==1000);

        int a3 = 100;
        int b3 = 100;
        System.out.println(a3==b3);

        int a4 = 1000;
        int b4 = 1000;
        System.out.println(a4==b4);

        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        System.out.println(str1==str2);
        System.out.println(str1==str3);

    }

    @Test
    public void test2() {
        byte a = 127;
        byte b = 127;
//        b = a+b;
        b += a;
        System.out.println("b="+b);
    }

    @Test
    public void test3() {
        System.out.println(1 << 20);
        System.out.println("tableSizeFor(-1) " + tableSizeFor(-1));
        System.out.println("tableSizeFor(0) " + tableSizeFor(0));
        System.out.println("tableSizeFor(1) " + tableSizeFor(1));
        System.out.println("tableSizeFor(3) " + tableSizeFor(3));
        System.out.println("tableSizeFor(4) " + tableSizeFor(4));
        System.out.println("tableSizeFor(5) " + tableSizeFor(5));
        System.out.println("tableSizeFor(10) " + tableSizeFor(10));
        System.out.println("tableSizeFor(30) " + tableSizeFor(30));
        System.out.println("tableSizeFor(1048576) " + tableSizeFor(1048576));
    }

    static int MAXIMUM_CAPACITY = 1 << 30;

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


}
