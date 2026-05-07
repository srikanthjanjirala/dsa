package com.dsa.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assessment")
public class AssesmentController {


    // suggest method name for below code snippet
    @GetMapping("/test")
    public void test() {

    }

    @GetMapping("/objectCastingTest")
    public void objectCastingTest() {
        class Foo
        {
            public int i = 3;
        }
        Object o = (Object)new Foo();
        Foo foo = (Foo)o;
        System.out.println("i = " + foo.i);
    }

    @GetMapping("/streamTest")
    public void streamTest() {
        String arr[] = {"java", "python", "java", "c++", "python"};

        Arrays.stream(arr).forEach(System.out::println);
    }

    @GetMapping("/arrayListTest")
    public void arrayListTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");

        // this line does nothing useful
        Collections.unmodifiableList(list);
        list.add("Three");
        for (String s: list){
            System.out.println(s);
        }

//      Output :  One Two Three

//        unmodifiableList creates a read-only (immutable view) of the given list
        List<String> readOnlyList = Collections.unmodifiableList(list);
        readOnlyList.add("Four"); // ❌ throws UnsupportedOperationException
    }

    @GetMapping("/linkedListTest")
    public void linkedListTest() {
        List<String> list1 = new LinkedList<>();
        list1.add("A");
        list1.add("B");
        list1.add("A");
        list1.add("C");
        list1.add("D");

        List<String> list2 = new LinkedList<>();
        list2.add("A");

        list1.removeAll(list2);
        System.out.println(list1);

//        Output = "B C D"
    }

    @GetMapping("/toBinary")
    public void toBinary() {
        int num = 17;
        System.out.println(Integer.toBinaryString(num));
//        1   → 1
//        2   → 10
//        3   → 11
//        4   → 100
//        5   → 101
//        6   → 110
//        7   → 111
//        8   → 1000
//        9   → 1001
//        10  → 1010
//        11  → 1011
//        12  → 1100
//        13  → 1101
//        14  → 1110
//        15  → 1111
//        16  → 10000
//        17  → 10001
//        18  → 10010
//        19  → 10011
//        20  → 10100
    }


    @GetMapping("/test2")
    public void test2() {
        try {
            int i, sum;
            sum = 10;
            for(i = -1; i < 3; i++) {
                sum = (sum / i);
            }
            System.out.println(sum);
        } catch (ArithmeticException e){
            System.out.println("0");
        }
    }

    @GetMapping("/printTheOutputOfIncrementOperation")
    public void printTheOutputOfIncrementOperation() {
        int sum = 0;
//        for (int i = 0,j=0;i<10 & j < 10; ++i,j=i+1) {
//        for (int i = 0;i<10; ++i) {
        for (int i=0;i<10; i++) {
            sum += i;
            System.out.println(sum);
        }
//        0
//        1
//        3
//        6
//        10
//        15
//        21
//        28
//        36
//        45

        for (int i=0,j=0;i<10 & j < 10; i++,j=i+1) {
            sum += i;
            System.out.println(sum);
        }
        //        0
        //        1
        //        3
        //        6
        //        10
        //        15
        //        21
        //        28
        //        36
    }

    @GetMapping("/prepost-increment")
    public void prePostIncrement() {
        int a = 5;
        int b = a++;  // post-increment
//        b = 5
//        a = 6

//        int a = 5;
//        int b = ++a;  // pre-increment
//
//        a = 6
//        b = 6
    }
}