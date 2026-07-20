package com.cognizant.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaOrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaOrmApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(DepartmentRepository departmentRepo, EmployeeRepository employeeRepo) {
        return args -> {
            System.out.println("--- Bootstrapping Data (O/R Mapping) ---");
            
            Department eng = new Department("Engineering");
            eng.addEmployee(new Employee("Alice", "Smith"));
            eng.addEmployee(new Employee("Bob", "Jones"));
            
            Department hr = new Department("Human Resources");
            hr.addEmployee(new Employee("Charlie", "Brown"));
            hr.addEmployee(new Employee("Diana", "Prince"));

            departmentRepo.save(eng);
            departmentRepo.save(hr);

            System.out.println("--- Query Method: findByFirstNameStartingWith('A') ---");
            employeeRepo.findByFirstNameStartingWith("A").forEach(System.out::println);

            System.out.println("--- Query Method: findByLastNameContainingIgnoreCase('one') ---");
            employeeRepo.findByLastNameContainingIgnoreCase("one").forEach(System.out::println);

            System.out.println("--- Query Method: findByDepartmentName('Human Resources') ---");
            employeeRepo.findByDepartmentName("Human Resources").forEach(System.out::println);
            
            System.out.println("Application execution completed successfully.");
        };
    }
}
