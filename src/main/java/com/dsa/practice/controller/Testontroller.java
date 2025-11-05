package com.dsa.practice.controller;

import com.dsa.practice.service.OfferService;
import org.hibernate.jdbc.Work;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@ControllerAdvice
@RequestMapping("/api/test")
public class Testontroller {

    @GetMapping("/test")
    public void test(
            @RequestParam String test,
            @RequestParam String test2
    ){
        OfferService percert = amount -> amount - (amount * 0.10);

        System.out.println(percert.apply(1000));
    }
























    @GetMapping("/odd-even")
    public void oddEvenFirstLetter(){
        String str = "my name is srikanth";
        String[] arr = str.split("\\s+");
        StringBuilder out = new StringBuilder();
        for (int i=0;i<arr.length;i++){
            String word = arr[i];
            if (i % 2 == 0){
                out.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
            } else {
                out.append(word.toLowerCase());
            }
            out.append(" ");
        }
        System.out.println(out);

    }

    @GetMapping("/reverse-string")
    public void reverseString(){
        String inp = "srikanth";
        StringBuilder out = new StringBuilder();
        for (int i=inp.length() -1;i >=0;i--){
            out.append(inp.charAt(i));
        }
        System.out.println(out);
    }

    @GetMapping("/count-string")
    public void countString(){
        String inp = "my name is srikanth";
        System.out.println("total character length"+inp.length());
        Long count = inp.chars().filter(c -> c == ' ').count();
        System.out.println("spaces"+ count);

        String[] arr = inp.split("\\s+");
        System.out.println(arr.length);
    }

//    get-only-numeric-value
    @GetMapping("/get-only-numeric-value")
    public void getOnlyNumericValue(){
        String inp = "my name is srikanth 1515";
        StringBuilder out = new StringBuilder();
        for (int i=0;i<inp.length();i++){
            char ch = inp.charAt(i);
            if (Character.isDigit(ch)){
//            if (Character.isLetter(ch)){
                out.append(ch);
            }
        }
        System.out.println(out);
    }

//    get Max Number

    @GetMapping("/max-number")
    public void maxNumber(){
        List<Integer> inp = Arrays.asList(1,4,45,7,82,4,6,5,8);
        Integer max = inp.get(0);
        for (int i=1;i<inp.size();i++){
            if (inp.get(i) > max){
                max = inp.get(i);
            }
        }
        System.out.println(max);

//        Option 2
//        String str = "2,1,5,4,58,65,2,4,6";
//        String[] inp = str.split(",");
//        Long max = Long.parseLong(inp[0]);
//
//        for (int i=0;i<inp.length;i++){
//            Long getMax = Long.parseLong(inp[i]);
//            if(getMax > max){
//                max = getMax;
//            }
//        }
//        System.out.println(max);

//        Option - 3
//        List<Integer> inp = Arrays.asList(1,4,5,4,65,8,4,5,98,7);
//        Integer max = inp.stream()
//                .max(Integer::compare) // method reference
//              .max((a,b) -> Integer.compare(a,b))
//                .get();
//
//        System.out.println(max);
    }

//    get only start with 1
    @GetMapping("/start-with-one")
    public void startWith(){
        String inp = "1,2,4,5,6,5,85,45,10,1465";
        List<String> collect = Arrays.stream(inp.split(","))
                .filter(s -> s.startsWith("1"))
                .collect(Collectors.toList());

        System.out.println(collect);

//        List<Integer> inp = Arrays.asList(1,2,4,5,6,8,89,4,6,5,15,78,18);
//
//        List<Integer> out = inp.stream()
//                  .filter(c -> String.valueOf(c).startsWith("1"))
//                .collect(Collectors.toList());
//
//        System.out.println(out);
    }

    @GetMapping("/ordering")
    public void ordering(){
        List<Integer> inp = Arrays.asList(1,2,4,5,7,8,5,2,4,56,4);
        TreeSet out =  new TreeSet<>();
        for (int i=0;i<inp.size();i++){
            out.add(inp.get(i));
        }
        System.out.println(out);

//          List<Integer> arr = Arrays.asList(1,45,4,15,2,46,16,75,16);
//          List<Integer> out = arr.stream()
//                .sorted(Comparator.reverseOrder())
//                .collect(Collectors.toList());
    }

    @GetMapping("/substring")
    public void substring(){
        String str = "abc";
        List<String> out = new ArrayList<>();
        for ( int i=0;i<str.length();i++){
            for (int j=i+1;j<=str.length();j++){
                String subStr = str.substring(i,j);
                out.add(subStr);
            }
        }
        System.out.println(out);
    }

    @GetMapping("/max-char")
    public void maxChar(@RequestParam String test){
        String str = "developer";
        Map<String,Integer> arr = new HashMap<>();
        for (int i=0;i<str.length();i++){
            String ch = Character.toString(str.charAt(i));
            arr.put(ch,arr.getOrDefault(ch,0) + 1);
        }
        System.out.println(arr);

        Integer maxVal = 0;
        String maxKey = "";
        for (Map.Entry<String,Integer> val : arr.entrySet()){
            System.out.println(val.getValue() + " " +val.getKey());
            if(val.getValue() > maxVal){
                maxVal = val.getValue();
                maxKey = val.getKey();
            }
        }
        System.out.println("max key "+ maxVal  +" count "+ maxKey);

//        String str = "developer";
//        Map<String, Long> collect = Arrays.stream(str.split(""))
//                .collect(Collectors.groupingBy(
//                        c -> c,
//                        Collectors.counting()
//                ));
//        System.out.println(collect);
    }

    @GetMapping("/palindram")
    public void palindram(@RequestParam String test){
        String str = test;
        StringBuilder revers = new StringBuilder(str).reverse();
        if(revers.toString().equals(str)){
            System.out.println("Yes is palindram");
        } else {
            System.out.println("Not a palindram");
        }
    }

    @GetMapping("/check-anagram")
    public void checkAnagram(
            @RequestParam String test,
            @RequestParam String test2
    ){
        char[] str = test.replaceAll("\\s+","").toLowerCase().toCharArray();
        char[] str2 = test2.replaceAll("\\s+","").toLowerCase().toCharArray();

        Arrays.sort(str);
        Arrays.sort(str2);

        if (Arrays.equals(str,str2)){
            System.out.println("Is anagram");
        } else {
            System.out.println("Is not anagram");
        }
    }

    @GetMapping("/salaryFilter")
    public void salaryFilter(@RequestParam String test){

        List<Map<String,Object>> employees = new ArrayList<>();

        Map<String,Object> emp1 = new HashMap<>();
        emp1.put("firstname","srikanth");
        emp1.put("salary",1000);
        emp1.put("department","it");

        Map<String,Object> emp2 = new HashMap<>();
        emp2.put("firstname","sagar");
        emp2.put("salary",2000);
        emp2.put("department","management");

        Map<String,Object> emp3 = new HashMap<>();
        emp3.put("firstname","sachine");
        emp3.put("salary",3000);
        emp3.put("department","hr");

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);

        List<Map<String, Object>> collect = employees.stream()
                .map(em -> {
                    Integer sal = (int) em.get("salary");
                    Integer deduct;
                    if (sal <= 1000) {
                        deduct = sal * 10 / 100;
                    } else if (sal <= 2000) {
                        deduct = sal * 20 / 100;
                    } else if (sal <= 3000) {
                        deduct = sal * 30 / 100;
                    } else {
                        deduct = sal * 40 / 100;
                    }
                    em.put("salary", sal - deduct);
                    return em;
                })
                .collect(Collectors.toList());
//                        .mapToInt(em -> (int) em.get("salary"))
//                .sum();

        System.out.println(collect);
    }

    @GetMapping("/swap-bumbers")
    public void swapNumbers(
            @RequestParam String test,
            @RequestParam String test2
    ){
//        Reguar swap
//        Integer a = 20,b = 30, c = 40;
//        Integer temp = a;
//        a = b;
//        b = c;
//        c = a;
//        System.out.println("after swap : a= "+a+" b ="+b+" c "+ c);

//        with additon and substraction
//        Integer a=10,b=20;
//
//        a = a+b;
//        b = a-b;
//        a = a-b;
//
//        System.out.println(a +" "+ b);

//        without using the addition and substraction

//        Bitwise XOR with 2 numbers
//        Integer a=10,b=20;
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//
//        System.out.println("a ="+a+" b "+b);

//        Bitwise XOR with 3 numbers
//        Integer a=20,b=30,c=40;
//        a = a^b^c;
//        b = a^b^c;
//        c = a^b^c;
//        a = a^b^c;
//        System.out.println("Input a="+a+" b="+b+" c="+ c);
    }
}
