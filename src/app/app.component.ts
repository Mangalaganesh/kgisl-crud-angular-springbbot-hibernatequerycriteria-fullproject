import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isLoggedIn = false; // Flag to control sidebar visibility

  

  setLoginState(state: boolean) {
    this.isLoggedIn = state; // Method to update the login state
  }
}
