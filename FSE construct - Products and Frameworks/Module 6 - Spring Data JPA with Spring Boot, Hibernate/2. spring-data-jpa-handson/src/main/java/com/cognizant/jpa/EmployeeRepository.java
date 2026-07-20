package com.cognizant.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByFirstNameStartingWith(String prefix);

    List<Employee> findByLastNameContainingIgnoreCase(String keyword);

    List<Employee> findByDepartmentName(String departmentName);
}
