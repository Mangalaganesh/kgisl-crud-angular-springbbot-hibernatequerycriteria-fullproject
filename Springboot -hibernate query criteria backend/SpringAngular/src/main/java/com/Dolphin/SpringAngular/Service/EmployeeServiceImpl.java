package com.Dolphin.SpringAngular.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dolphin.SpringAngular.DAO.AddressDAO;
import com.Dolphin.SpringAngular.DAO.EmployeeDAO;
import com.Dolphin.SpringAngular.Model.Address;
import com.Dolphin.SpringAngular.Model.Address_view;
import com.Dolphin.SpringAngular.Model.Employee;
import com.Dolphin.SpringAngular.Model.EmployeeDTO;
import com.Dolphin.SpringAngular.Model.EmployeeWithAddresses;
import com.Dolphin.SpringAngular.exception.ResourceNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
    private EmployeeDAO employeeDAO;
	
	@Autowired
    private AddressDAO addressDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	
//	@Override
//    public List<Employee> getAllEmployees() {
//        return employeeDAO.findAll();
//    }
	

	//=====view=======
//	@Override
//	public List<EmployeeWithAddresses> getAllEmployees() {
//        return employeeDAO.findAllEmployeesWithAddresses();
//    }
	
	@Override
	public List<EmployeeDTO> getAllEmployees() {
        return employeeDAO.findAllEmployeesWithAddresses();
    }


    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDAO.findById(id);
    }


    
    @Override
    public Employee createEmployee(Employee employee) {
        // Save the employee first, which will also save the addresses due to the CascadeType.ALL
        Employee savedEmployee = employeeDAO.save(employee);
        return savedEmployee;
    }

    
    @Transactional
    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
    	
        Employee existingEmployee = employeeDAO.findById(id);
        if (existingEmployee == null) {
            throw new ResourceNotFoundException("Employee not exist with id: " + id);
        }

        // Update the existing employee's fields
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmailId(employeeDetails.getEmailId());
        existingEmployee.setDesignation(employeeDetails.getDesignation());

        // Clear current addresses
        existingEmployee.getAddresses().clear();

        // Add new addresses from the request
        for (Address address : employeeDetails.getAddresses()) {
        	
            Address newAddress = new Address();
            
            newAddress.setStreet(address.getStreet());
            newAddress.setCity(address.getCity());
            newAddress.setState(address.getState());
            newAddress.setEmployee(existingEmployee); // Set the employee reference
            
            // Add the new address to the employee's address list
            existingEmployee.getAddresses().add(newAddress);
        }

        // Merge the updated employee (including addresses) to the database
        employeeDAO.update(existingEmployee);

        // Return the updated employee
        return existingEmployee;
    }



    @Transactional
    @Override
    public void deleteEmployee(Long id) {
        employeeDAO.deleteById(id);
    }
	
	
	
}
