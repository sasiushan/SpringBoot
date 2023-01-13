package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService
{
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployees()
    {
        return employeeRepo.findAll();
    }

    public void addEmployees(Employee employee)
    {
        if(employee.getFirstName() == null || employee.getLastName() == null || employee.getEmail()== null)
        {
            System.out.println("Please enter valid input");
        }
        else
        {
            System.out.println("Successfully stored in database");
            employeeRepo.save(employee);
        }
    }

    public Optional<Employee> getById(Long id)
    {
        Optional<Employee> tempEmployee;
        Optional<Employee> byId = employeeRepo.findById(id);
        if(byId.isPresent())
        {
            tempEmployee=employeeRepo.findById(id);
        }
        else
        {
            throw new IllegalStateException("Employee with Id:" + id + " is not present");
        }
        return tempEmployee;
    }

    public void deleteEmployee(Long id)
    {
        Optional<Employee> employeeId = employeeRepo.findById(id);
        if(employeeId.isPresent())
        {
            employeeRepo.deleteById(id);
            System.out.println("Successfully deleted employee with id" + id);
        }
        else
        {
            throw new IllegalStateException("Cannot find employee with id:" + id+ " in the database");
        }
    }

//    public void updateEmployee(@NonNull Long id, Employee employeeDetails)
//    {
//       Employee employee = employeeRepo.findById(id).orElseThrow(()-> new IllegalStateException("student id " + id + "does not exist"));
//
//       if(employeeDetails.getFirstName().length()>0 && !Objects.equals(employee.getFirstName(), employeeDetails.getFirstName()))
//        {
//            employee.setFirstName(employeeDetails.getFirstName());
//        }
//       if(employeeDetails.getLastName().length()>0 && !Objects.equals(employee.getLastName(), employeeDetails.getLastName()))
//       {
//           employee.setLastName(employeeDetails.getLastName());
//       }
//       if(employeeDetails.getEmail().length()>0 && !Objects.equals(employee.getEmail(), employeeDetails.getEmail()))
//       {
//           employee.setEmail(employeeDetails.getEmail());
//       }
//       employeeRepo.save(employee);
//    }


    public ResponseEntity<Employee> updateEmployee(@NonNull Long id, @NonNull Employee employeeDetails)
    {
            Employee employeeTemp = employeeRepo.findById(id).orElseThrow(()-> new IllegalStateException("Employee with id "+ "id is not found in database"));
            employeeTemp.setFirstName(employeeDetails.getFirstName());
            employeeTemp.setLastName(employeeDetails.getLastName());
            employeeTemp.setEmail(employeeDetails.getEmail());

            Employee updateEmployee = employeeRepo.save(employeeTemp);
            return ResponseEntity.ok(updateEmployee);
    }
}
