package ibf2022.ssf.day13_lecture_practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.ssf.day13_lecture_practice.model.Employee;
import ibf2022.ssf.day13_lecture_practice.repository.EmployeeRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model) {

        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @GetMapping("/addnew")
    public String addPage(Model model) {

        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

    // model must be last
    @PostMapping("/saveEmployee")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        Boolean boolResult = false;
        boolResult = empRepo.save(employeeForm);
        return "redirect:/employees/home";
    }

    @GetMapping("/deleteEmployee/{email}")
    public String deleteEmployee(@PathVariable("email") String email) {
        Employee emp = empRepo.findByEmailID(email);
        Boolean boolResult = empRepo.delete(emp);

        return "redirect:/employees/home";
    }

    @GetMapping("/updateEmployee/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model) {
        Employee emp = empRepo.findByEmailID(email);
        model.addAttribute("employee",emp);
        Boolean boolResult = empRepo.delete(emp);

        return "employeeupdate";
    }

    public String updEmployeeProcess(@ModelAttribute("employee") Employee emp, BindingResult result, Model model){

        if (result.hasErrors()) {
            return "employeeupdate";
        }
        empRepo.updateEmployee(emp);


        return "redirect:/employees/home";
    }


}
