package ibf2022.ssf.day13_lecture_practice.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import ibf2022.ssf.day13_lecture_practice.model.Employee;

@Repository
public class EmployeeRepo {

    public List<Employee> employees;

    public EmployeeRepo() throws ParseException {

        if (employees == null) {
            employees = new ArrayList<Employee>();
        }

        // String date = "2020-01-01";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date birthday = df.parse("2020-02-02");
        Employee emp = new Employee("Brown", "Mister", "mrbrown@brown.com", "88889999", 400000, birthday,
                "Brown Street", 123456);
        employees.add(emp);

    }

    public List<Employee> findAll() {
        return employees;
    }

    public boolean save(Employee employee) {
        Boolean result = employees.add(employee);
        return result;
    }

    public boolean delete (Employee employee){

        // Employee e = employees.stream().filter(emp->emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst().get();
        int employeeIndex = employees.indexOf(employee);
        Boolean result = false;
        if(employeeIndex >= 0){
            employees.remove(employeeIndex);
            result = true;
        }
        return result;
    }

    public Employee findByEmailID(String email){

        Employee emp = employees.stream().filter(e->e.getEmail().equalsIgnoreCase(email)).findFirst().get();
        return emp;
    }
    
    public Boolean updateEmployee(Employee em){
        Employee emp = employees.stream().filter(e->e.getEmail().equalsIgnoreCase(em.getEmail())).findFirst().get();
        
        Integer idx =0;
        if(emp != null){
            idx = employees.indexOf(emp);
            employees.remove(idx);
            employees.add(em);
            return true;
        }
        else
            return false;
    }

}
