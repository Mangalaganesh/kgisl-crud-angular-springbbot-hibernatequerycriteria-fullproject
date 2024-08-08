import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
 
@Component({
  selector: 'app-employee-view',
  templateUrl: './employee-view.component.html',
  styleUrls: ['./employee-view.component.css']
})
export class EmployeeViewComponent implements OnInit {
  employees: Employee[] = [];

 
  constructor(private employeeService: EmployeeService,
    private router: Router
  ) { }
 
  ngOnInit(): void {
    this.getEmployees();
  }
 
  getEmployees(): void {
    this.employeeService.getAllEmployees().subscribe(
      (data) => {
        this.employees = data;
      },
      (error) => {
        console.error('Error fetching employees', error);
      }
    );
  }

  updateEmployee(employee: Employee): void {
    // console.log('Navigating to employee registration with:', employee);
    console.log('Navigating to employee registration with ID:', employee.emp_Id);
    this.router.navigate(['employeeregistration', employee.emp_Id]).then(success => {
      if (success) {
        console.log('Navigation successful');
      } else {
        console.error('Navigation failed');
      }
    });
  }

  deleteEmployee(employee: Employee): void {
    // Prompt the user for confirmation
    if (confirm(`Are you sure you want to delete employee ${employee.name}?`)) {
      if (employee.emp_Id !== undefined) {
        // Call the service to delete the employee
        this.employeeService.deleteEmployee(employee.emp_Id).subscribe(
          () => {
            // On success, remove the employee from the local list
            this.employees = this.employees.filter(e => e.emp_Id !== employee.emp_Id);
            console.log('Employee deleted:', employee);
          },
          (error) => {
            console.error('Error deleting employee', error);
          }
        );
      } else {
        console.error('Employee ID is missing');
      }
    } else {
      console.log('Deletion canceled');
    }
  }


}
