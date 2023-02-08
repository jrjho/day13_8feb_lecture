package ibf2022.ssf.day13_lecture_practice.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

    final String dirPath = "/Users/jrjho/data";
    final String filename = "employee.txt";
    public List<Employee> employees;

    //need to modify this so that the initial employee data is read from txt file.
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

    public boolean save(Employee employee) throws FileNotFoundException{
        Boolean result = employees.add(employee);

        File f = new File(dirPath + "/" + filename);
        OutputStream os = new FileOutputStream(f,true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();

        return result;
    }

    public boolean delete (Employee employee)throws FileNotFoundException{

        // Employee e = employees.stream().filter(emp->emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst().get();
        int employeeIndex = employees.indexOf(employee);
        Boolean result = false;
        if(employeeIndex >= 0){
            employees.remove(employeeIndex);
            result = true;

            File f = new File(dirPath + "/" + filename);
            OutputStream os = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(os);
            pw.println(employees.toString());
            pw.flush();
            pw.close();
        }
        return result;
    }

    public Employee findByEmailID(String email){

        Employee emp = employees.stream().filter(e->e.getEmail().equalsIgnoreCase(email)).findFirst().get();
        return emp;
    }
    
    
    public Boolean updateEmployee(Employee em) throws FileNotFoundException{
        // Employee emp = employees.stream().filter(e->e.getEmail().equalsIgnoreCase(em.getEmail())).findFirst().get();
        
        // Integer idx =0;
        // if(emp != null){
        //     idx = employees.indexOf(emp);
        //     employees.remove(idx);
        //     employees.add(em);
        //     return true;
        // }
        // else
        //     return false;
        Employee emp = employees.stream().filter(e -> e.getEmail().equals(em.getEmail())).findFirst().get();

        int employeeIndex = employees.indexOf(emp);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
        }

        employees.add(em);
        File f = new File(dirPath + "/" + filename);
            OutputStream os = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(os);
            pw.println(employees.toString());
            pw.flush();
            pw.close();

        return true;
    }

}
