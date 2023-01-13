package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
//crossOrigin annotation permits request in and out of the server
@CrossOrigin("*/")
public class EmployeeController {

   private final EmployeeService employeeService;

   @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public void insertEmployees(@RequestBody @NonNull Employee employee)
    {
        employeeService.addEmployees(employee);
    }

    @GetMapping(path = "/employees/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id)
    {
        return employeeService.getById(id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable Long id)
    {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
   public ResponseEntity<Employee> updateById(@PathVariable(value = "id") Long id, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException
   {
      return employeeService.updateEmployee(id, employeeDetails);
   }

//    @PutMapping("/employees/{id}")
//  public void updateById(@PathVariable Long id, @RequestBody Employee employeeDetials)
//  {
//      employeeService.updateEmployee(id, employeeDetials);
//  }



}
