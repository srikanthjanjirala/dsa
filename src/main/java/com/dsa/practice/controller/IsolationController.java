package com.dsa.practice.controller;

import com.dsa.practice.model.Employee;
import com.dsa.practice.service.IsolationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/isolation")
public class IsolationController {

    private final IsolationService isolationService;

    @GetMapping("/get-all-emp")
    public List<Employee> getAllEmployees(){
        return isolationService.getAllEmployees();
    }

    // Dirty reads start
    // Start Transaction A
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id) {

        new Thread(() -> {
            try {
                isolationService.updateSalary(id);
            } catch (Exception e) {
                System.out.println("Transaction A rolled back");
            }
        }).start();

        return "Update started...";
    }

    // Trigger Transaction B
    @GetMapping("/read/{id}")
    public String read(@PathVariable Long id) {
        isolationService.readSalary(id);
        return "Read done";
    }
//    Dirty reads end


    // Non repeatable reads start
    //  INSERT INTO employee (name, salary, department, flag) VALUES ('John', 5000, 'IT', true);
    // Start Transaction A
    @GetMapping("/non-repeatable/{id}")
    public String testNonRepeatable(@PathVariable Long id) {

        new Thread(() -> isolationService.nonRepeatableRead(id)).start();

        return "Transaction A started...";
    }

    // Start Transaction B
    @GetMapping("/non-repeatable/update/{id}")
    public String updateNonRepeatable(@PathVariable Long id) {

        isolationService.updateSalaryNonRepeatable(id);
        return "Transaction B completed...";
    }

    // Non repeatable reads end
}
