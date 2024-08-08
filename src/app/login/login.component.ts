import { Component,Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  @Output() loginStatusChanged = new EventEmitter<boolean>(); // Add an event emitter

  constructor(private router: Router) {}

  login() {
    if (this.username === 'admin' && this.password === 'admin') {
      this.loginStatusChanged.emit(true); // Emit true when login is successful
      this.router.navigate(['/employeeregistration']);
    } else {
      alert('Invalid credentials');
    }
  }
}
