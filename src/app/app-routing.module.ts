import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

import { MainMenuComponent } from './main-menu/main-menu.component';
import { EmployeeViewComponent } from './employee-view/employee-view.component';
import { EmployeeRegistrationComponent } from './employeeregistration/employeeregistration.component';
import { SidebarComponent } from './sidebar/sidebar.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'mainmenu', component: MainMenuComponent },
   { path: 'employeeregistration', component: EmployeeRegistrationComponent },
   { path: 'employee-view', component: EmployeeViewComponent },
   { path: 'employeeregistration/:id', component: EmployeeRegistrationComponent },
  { path: 'sidebar', component: SidebarComponent, children: [
    { path: 'employeeregistration', component: EmployeeRegistrationComponent },
    { path: 'employee-view', component: EmployeeViewComponent },
    { path: 'employeeregistration/:id', component: EmployeeRegistrationComponent }
  ]
},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
