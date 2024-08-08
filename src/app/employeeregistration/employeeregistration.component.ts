import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Address, Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { NgForm } from '@angular/forms';



@Component({
  selector: 'app-employeeregistration',
  templateUrl: './employeeregistration.component.html',
  styleUrls: ['./employeeregistration.component.css']
})
export class EmployeeRegistrationComponent implements OnInit{

   employee: Employee = new Employee();


  isEditing: boolean = false; // To track if we're editing
  

  constructor(private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute
  ) { 
    // Initialize with a default address
    this.employee.addresses = [new Address()]; // Add a default address
  }

  ngOnInit(): void {
    const empId = this.route.snapshot.paramMap.get('id'); // Get the ID from the route
    if (empId) {
      this.isEditing = true;
      this.getEmployeeById(+empId); // Fetch the employee by ID
    }
  }

  getEmployeeById(empId: number): void {
    const obj=this;
    this.employeeService.getEmployeeById(empId).subscribe(
      (data) => {
        obj.employee = data;
        console.log('Employee data loaded:', obj.employee);
      },
      (error) => {
        console.error('Error fetching employee', error);
      }
    );
  }

  addAddress() {
    this.employee.addresses.push(new Address());
  }

  removeAddress(index: number) {
    this.employee.addresses.splice(index, 1);
  }

  registerEmployee(employeeForm: NgForm) {
    if (employeeForm.valid) {
      if (this.isEditing) {
        this.updateEmployee();
      } else {
        this.employeeService.registerEmployee(this.employee).subscribe(response => {
          console.log('Employee registered successfully', response);
          alert('Employee registered successfully!');
          this.router.navigate(['/employee-view']);
        }, error => {
          console.error('Error registering employee', error);
          alert('Failed to register employee. Please try again.');
        });
      }
    } else {
      console.log('Form is invalid');
    }
  }




  updateEmployee() {
    // Assume you have an update endpoint in your service
    this.employeeService.updateEmployee(this.employee).subscribe(response => {
      console.log('Employee updated successfully', response);
      alert('Employee updated successfully!');
      this.router.navigate(['/employee-view']); 
    }, error => {
      console.error('Error updating employee', error);
      alert('Failed to update employee. Please try again.');
    });
  }
}
