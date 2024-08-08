package com.Dolphin.SpringAngular.Service;

import java.util.List;

import com.Dolphin.SpringAngular.Model.Employee;
import com.Dolphin.SpringAngular.Model.EmployeeDTO;
import com.Dolphin.SpringAngular.Model.EmployeeWithAddresses;

public interface EmployeeService {

	    //List<Employee> getAllEmployees();
	    //List<EmployeeWithAddresses> getAllEmployees();
	    List<EmployeeDTO> getAllEmployees();
	    Employee getEmployeeById(Long id);
	    Employee createEmployee(Employee employee);
	    Employee updateEmployee(Long id, Employee employee);
	    void deleteEmployee(Long id);
	
}
