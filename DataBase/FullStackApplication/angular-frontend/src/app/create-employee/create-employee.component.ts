import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  constructor(private employeeService: EmployeeService, private router: Router ) 
  { }

  ngOnInit(): void 
  {

  }

  saveEmployee() //function that is used to save an employee
  {
    this.employeeService.createEmployee(this.employee).subscribe(data =>
      {
        console.log('saved to database');
      }, 
      error => console.log(error));
      this.goToEmployeeList();
  }

  goToEmployeeList() //once we are done saving an employee we need to redirect to the database page
  {
    this.router.navigate(['/employees']);
  }

  onSubmit(): void
  {
    this.saveEmployee();
  }
}
