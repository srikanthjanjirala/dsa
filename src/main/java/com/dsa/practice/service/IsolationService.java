package com.dsa.practice.service;

import com.dsa.practice.model.Employee;
import com.dsa.practice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class IsolationService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }


//    Dirty Reads start
//    INSERT INTO employee (name, salary, department, flag) VALUES ('John', 5000, 'IT', true);
    // 🔴 Transaction A (Update but NOT commit)
    @Transactional
    public void updateSalary(Long id) {

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setSalary(99999.0); // temporary value

        employeeRepository.save(emp);

        System.out.println("Transaction A: Salary updated but not committed");

        try {
            Thread.sleep(10000); // wait 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ❌ Force rollback
        throw new RuntimeException("Transaction A rolled back");
    }

    // 🟢 Transaction B (Dirty Read)
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readSalary(Long id) {

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        System.out.println("Transaction B reads salary: " + emp.getSalary());
    }
    //    Dirty Reads end


    // Non repeatable reads start
    // 🔴 Transaction A (Read twice)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void nonRepeatableRead(Long id) {

        Employee emp1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        System.out.println("First Read Salary: " + emp1.getSalary());

        try {
            Thread.sleep(10000); // wait so another transaction updates
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Employee emp2 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        System.out.println("Second Read Salary: " + emp2.getSalary());
    }

    // 🟢 Transaction B (Update and commit)
    @Transactional
    public void updateSalaryNonRepeatable(Long id) {

        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setSalary(8000.0);
        employeeRepository.save(emp);

        System.out.println("Transaction B: Salary updated to 8000");
    }
    // Non repeatable reads end
}
