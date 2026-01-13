package com.dsa.practice.controller;

import com.dsa.practice.dto.EmployeeResponse;
import com.dsa.practice.model.Employee;
import com.dsa.practice.service.PaymentService;
import com.dsa.practice.util.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
@RequestMapping("/api/test")
public class Testontroller {

    @Autowired
    private PaymentService paymentService; // User @Primary -> paypal

//    @Autowired
//    @Qualifier("stripe")
//    private PaymentService paymentService1; // User @Qualifier -> stripe
    @GetMapping("/test")
    public void test(
//            @RequestParam Integer n
    ){
        List<Employee> empList = Arrays.asList(
                new Employee(1L, "srikanth", 80000d, "it", true),
                new Employee(2L, "sagar", 60000d, "it", true),
                new Employee(3L, "sachin", 30000d, "hr", true),
                new Employee(4L, "vishal", 50000d, "hr", true)
        );

        List<EmployeeResponse> getAllEmp = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet()
                .stream()
                .map(entity -> {
                    List<Employee> value = entity.getValue();
                    Employee allEmpList = value.stream()
                            .max(Comparator.comparing(Employee::getSalary))
                            .orElseThrow(() -> new IllegalStateException("No Employee Found"));

                    return new EmployeeResponse(
                            allEmpList.getId(),
                            allEmpList.getName(),
                            allEmpList.getSalary(),
                            entity.getKey(),
                            value.size()
                    );
                })
                .collect(Collectors.toList());

        System.out.println(getAllEmp);

//        Map<String, Employee> collect = empList.stream()
//                .collect(Collectors.groupingBy(
//                        Employee::getDepartment,
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
//                                Optional::get
//                        )
//                ));
//
//        System.out.println(collect);
    }




















    @GetMapping("/findHighestPaidEmployeeByDepartmentAndCount")
    public void findHighestPaidEmployeeByDepartmentAndCount(
            @RequestParam String name
    ){
        List<Employee> empList = Arrays.asList(
                new Employee(1L, "srikanth", 80000d, "it", true),
                new Employee(2L, "sagar", 60000d, "it", true),
                new Employee(3L, "sachin", 30000d, "hr", true),
                new Employee(4L, "vishal", 50000d, "hr", true)
        );

        List<EmployeeResponse> noEmployeeForDepartment = empList.stream()
                .filter(emp -> emp.isFlag())
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet() // map the methos
                .stream()
                .map(entry -> {
                    List<Employee> list = entry.getValue();
                    Employee highestPaidEmp = list.stream()
                            .max(Comparator.comparing(Employee::getSalary))
                            .orElseThrow(() ->
                                    new IllegalStateException("No employee for department"));

                    return new EmployeeResponse(
                            highestPaidEmp.getId(),
                            highestPaidEmp.getName(),
                            highestPaidEmp.getSalary(),
                            entry.getKey(),
                            list.size()
                    );
                })
                .collect(Collectors.toList());

        System.out.println(noEmployeeForDepartment);
    }

    @GetMapping("/sortEMployeeBySalaryInDesceding")
    public void sortEMployeeBySalaryInDesceding(
            @RequestParam String name
    ){
        List<Employee> empList = Arrays.asList(
                new Employee(1L, "srikanth", 80000d, "it", true),
                new Employee(2L, "sagar", 60000d, "it", true),
                new Employee(3L, "sachin", 30000d, "hr", true),
                new Employee(4L, "vishal", 50000d, "hr", true)
        );

        List<Employee> collect = empList.stream()
                .filter(emp -> emp.isFlag())
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                                        Optional::get
                                )
                        ))
                .values() // it will return value of map
                .stream()
                .sorted(Comparator.comparing(Employee::getId))
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    @GetMapping("/employeeSortWithSalary")
    public void employeeSortWithSalary(
            @RequestParam Integer test
    ){
        List<Employee> col = Arrays.asList(
                new Employee(1L, "srikanth", 80000d, "it", true),
                new Employee(2L, "sagar", 60000d, "it", true),
                new Employee(3L, "sachin", 30000d, "hr", true),
                new Employee(4L, "vishal", 50000d, "hr", true)
        );

        List<Employee> getCol = col.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println(getCol);
    }

    @GetMapping("/findSecondLargestNumber")
    public void findSecondLargestNumber(
            @RequestParam Integer test
    ){
        List<Integer> inp = Arrays.asList(11,2,42,61,14,23,26,71,9,10,68,11);
        Integer max = Integer.MIN_VALUE;
        Integer secondMax = Integer.MIN_VALUE;

        for (int num: inp){
            if (num > max){
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max){
                secondMax = num;
            }
            System.out.println(secondMax);
        }

//        List<Integer> inp = Arrays.asList(11,2,42,61,14,23,26,71,9,10,68,11);
//
//        inp.stream()
//                .skip(1)
//                .max(Integer::compareTo)
//                .get();

    }

    @GetMapping("/checkPrimeNumber")
    public void checkPrimeNumber(
            @RequestParam Integer test
    ){
        System.out.println(test+"is prime?"+ isPrime(test));
    }

    public static boolean isPrime(Integer n){
        if (n <= 1) return false;

        for (int i=2;i<n;i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    @GetMapping("/fibonacciSeries")
    public void fibonacciSeries(
            @RequestParam String test,
            @RequestParam String test2
    ){
        int n = 10;
        int a=0,b=1;

        System.out.println(a);
        System.out.println(b);

        for (int i=2;i<n;i++){
            int c=a+b;
            System.out.println(" "+ c);
            a = b;
            b = c;
        }
    }

    @GetMapping("/lamda-with-functional-interface")
    public void lamdaWithFunctionalInterface(
            @RequestParam String test,
            @RequestParam String test2
    ){
        Offer percent = amount -> amount - (amount * 0.10);

        System.out.println(percent.apply(1000));
    }

    @GetMapping("/removeDuplicateCharectoresFromString")
    public void removeDuplicateCharectoresFromString(
            @RequestParam String test,
            @RequestParam String test2
    ){
        List<List<String>> list = Arrays.asList(
                Arrays.asList("java", "java1", "java2"),
                Arrays.asList("java4", "java3", "java2"),
                Arrays.asList("java5", "java1", "java2")
        );

        List<String> collect = list.stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    @GetMapping("/department-with-salary")
    public void departmentWithSalary(
            @RequestParam String test,
            @RequestParam String test2
    ){
//        10000 10% 20000 20% 30000 30 30000 > 40
        List<Employee> getList = Arrays.asList(
                new Employee(1L,"srikanth",10000d,"it",true),
                new Employee(2L,"sagar",20000d,"it",true),
                new Employee(3L,"sachin",30000d,"hr",true),
                new Employee(4L,"mithlesh",40000d,"it",true)
        );
//        System.out.println(getList);
        List<Employee> collect = getList.stream()
                .map(emp -> {
                    Double salary = emp.getSalary();
                    Double deduct;
                    if (salary <= 1000) {
                        deduct = salary * 10 / 100d;
                    } else {
                        deduct = salary * 10 / 100d;
                    }
                    emp.setSalary(salary - deduct);
                    return emp;
                })
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    @GetMapping("/first-non-repeating-charector")
    public void firstNonRepeatingCharector(
            @RequestParam String test,
            @RequestParam String test2
    ){
        String str = "developer";
        Map<String,Integer> arr = new HashMap<>();
        for (int i=0;i<str.length();i++){
            String ch = Character.toString(str.charAt(i));
            arr.put(ch,arr.getOrDefault(ch,0) + 1);
        }
        System.out.println(arr);
        // Step 2: Find first non-repeating character
        for (int i = 0; i < str.length(); i++) {
            String ch = Character.toString(str.charAt(i));
            if (arr.get(ch) == 1) {
                System.out.println("First non-repeating character: " + ch);
                break;
            }
        }
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
        // Option 1
//        List<Integer> col = Arrays.asList(1,4,6,80,7,6);
//        Integer maxNum = col.get(0);
//        for (int i=0;i<col.size();i++){
//            Integer nm = col.get(i);
//            if (maxNum < nm){
//                maxNum = nm;
//            }
//        }
//        System.out.println(maxNum);

        // Option 2
//        List<Integer> col = Arrays.asList(1,4,6,80,7,6);
//        Integer max = col.stream()
//                .max(Integer::compareTo)
//                .get();
//        System.out.println(max);

        // Option 3
        String inp = "1,4,5,9,6,7,5,3,4,95,45";
        String[] arr = inp.split(",");
        Integer max = Integer.parseInt(arr[0]);
        for (int i=0;i<arr.length;i++){
            Integer getNum = Integer.parseInt(arr[i]);
            if (max < getNum){
                max = getNum;
            }
        }
        System.out.println(max);
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
//        c = test;
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

    @GetMapping("/highest-salry-of-department")
    public List<Map<String, Object>> highestSalaryOfDepartment(
            @RequestParam String test,
            @RequestParam String test2
    ){

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
        emp3.put("firstname","raju");
        emp3.put("salary",4000);
        emp3.put("department","hr");


        Map<String,Object> emp4 = new HashMap<>();
        emp4.put("firstname","sachine");
        emp4.put("salary",3000);
        emp4.put("department","hr");

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);


//        List<Map<String, Object>> itEmployees = employees.stream()
//                .filter(emp -> "it".equals(emp.get("department")))
//                .collect(Collectors.toList());

//        Map<Object, List<Map<String, Object>>> col = employees.stream()
//                .collect(Collectors.groupingBy(emp -> emp.get("department")));
        List<Map<String, Object>> collect = employees.stream()
                .collect(Collectors.groupingBy(
                        emp -> emp.get("department"),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(emp -> (Integer) emp.get("salary"))),
                                Optional::get
                        )
                ))
                .values()
                .stream()
                .collect(Collectors.toList());

        return collect;

        // get highest salary emp data as per the department
//        List<Employee> getData = Arrays.asList(
//                new Employee(1L,"srikanth",2000d,"it",true),
//                new Employee(2L,"sachin",1000d,"it",true),
//                new Employee(3L,"migthesh",1500d,"hr",true),
//                new Employee(4L,"sagar",3000d,"hr",true)
//        );
//
//        List<Employee> collect = getData.stream()
//                .collect(Collectors.groupingBy(
//                        Employee::getDepartment,
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
//                                Optional::get
//                        )
//                ))
//                .values()
//                .stream()
//                .collect(Collectors.toList());

    }

    @GetMapping("/longest-substring-without-repeating-characters")
    public void longestSubstringWithoutRepeatingCharacters()
    {
        String s = "abcabcbkdjshbb";
        int start = 0;
        int maxLength = 0;
        int maxStart = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) >= start) {
                start = map.get(ch) + 1; // move start to exclude repeating character
            }
            map.put(ch, i);
            if (i - start + 1 > maxLength) {
                maxLength = i - start + 1;
                maxStart = start;
            }
        }

        String longestSubstring = s.substring(maxStart, maxStart + maxLength);
        System.out.println("Longest substring without repeating characters: " + longestSubstring);
    }
}
