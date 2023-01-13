import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService 
{

  // firsrt we must input HttpClient as a param into the constructor and import it above as well
  //then create a baseUrl and set the path from springboot as baseUrl
  //then we create method called getEmployeeList() of type observable which is similar to collection in java
  //then to go employee-list.components.ts   

  private baseURL = "http://localhost:8080/api/v1/employees";

  constructor(private httpClient: HttpClient) { }

  getEmployeeList(): Observable<Employee[]> //get all from database
  {
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
    
  }

  createEmployee(employee: Employee): Observable<Object> //post object into database
  {
    return this.httpClient.post(`${this.baseURL}`, employee);
  }

  getEmployeeById(id: number): Observable<Employee> //get employee by id
  {
    return this.httpClient.get<Employee>(`${this.baseURL}/${id}`);
  }

  updateEmployee(id:number, employee: Employee):Observable<Object>
  {
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  } 

  deleteEmployee(id: number): Observable<Object>
  {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
