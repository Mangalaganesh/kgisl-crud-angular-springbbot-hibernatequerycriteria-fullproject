import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // Import FormsModule for ngModel
import { RouterModule, Routes } from '@angular/router'; // Import RouterModule for routing

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { EmployeeViewComponent } from './employee-view/employee-view.component';
import { EmployeeRegistrationComponent } from './employeeregistration/employeeregistration.component';
import { HttpClientModule } from '@angular/common/http';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AppRoutingModule } from './app-routing.module'; // Import the AppRoutingModule



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainMenuComponent,
    EmployeeViewComponent,
    EmployeeRegistrationComponent,
    SidebarComponent // Ensure this is declared
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule, // Add FormsModule here
    AppRoutingModule // Use the AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
