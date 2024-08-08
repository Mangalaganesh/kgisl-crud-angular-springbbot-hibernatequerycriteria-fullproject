import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/api/v1/employees';
 

  constructor(private http: HttpClient) { }

  registerEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.baseUrl}/register`, employee);
    
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}`);
  }

  deleteEmployee(emp_Id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${emp_Id}`);
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.baseUrl}/${employee.emp_Id}`, employee);
  }

  // New method to get an employee by ID
  getEmployeeById(emp_Id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseUrl}/${emp_Id}`);
  }


}
