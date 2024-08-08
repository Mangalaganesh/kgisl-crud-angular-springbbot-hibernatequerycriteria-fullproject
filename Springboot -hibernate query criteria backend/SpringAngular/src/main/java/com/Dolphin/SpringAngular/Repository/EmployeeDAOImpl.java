package com.Dolphin.SpringAngular.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.Dolphin.SpringAngular.DAO.AddressDAO;
import com.Dolphin.SpringAngular.DAO.EmployeeDAO;
import com.Dolphin.SpringAngular.Model.Address;
import com.Dolphin.SpringAngular.Model.Address_view;
import com.Dolphin.SpringAngular.Model.Employee;
import com.Dolphin.SpringAngular.Model.EmployeeDTO;
import com.Dolphin.SpringAngular.Model.EmployeeWithAddresses;
import com.Dolphin.SpringAngular.exception.ResourceNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
	


    @Autowired
    private AddressDAO addressDAO;
    
    @Autowired
    private EntityManager entityManager;
    
  
    
    //without view concept
    @Override
    public List<Employee> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        // Use a fetch join to fetch addresses along with employees
        root.fetch("addresses", JoinType.LEFT);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }
    

    //=====view=======
    @Override
    public List<EmployeeDTO> findAllEmployeesWithAddresses() {
    	
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeWithAddresses> query = cb.createQuery(EmployeeWithAddresses.class);
        Root<EmployeeWithAddresses> root = query.from(EmployeeWithAddresses.class);

        // Select employee data and associated addresses
        query.select(root).distinct(true);

        // Execute the query to get all employee data with addresses
        List<EmployeeWithAddresses> employeeWithAddressesList = entityManager.createQuery(query).getResultList();

        // Create a map to store employees by ID and collect addresses
        Map<Long, EmployeeDTO> employeeMap = new HashMap<>();

        for (EmployeeWithAddresses employee : employeeWithAddressesList) {
            Long empId = employee.getEmp_Id();
            
            // If the employee is not already in the map, add them
            if (!employeeMap.containsKey(empId)) {
                EmployeeDTO dto = new EmployeeDTO();
                dto.setEmp_Id(empId);
                dto.setName(employee.getName());
                dto.setEmail_Id(employee.getEmail_Id());
                dto.setDesignation(employee.getDesignation());
                dto.setAddresses(new ArrayList<>()); // Initialize addresses
                employeeMap.put(empId, dto);
            }

            // Add the address to the employee's address list
            EmployeeDTO existingEmployee = employeeMap.get(empId);
            if (employee.getAddresses() != null) {
                existingEmployee.getAddresses().addAll(employee.getAddresses());
            }
        }

        // Set address count for each employee
        for (EmployeeDTO employee : employeeMap.values()) {
            employee.setAddresscount(employee.getAddresses().size());
        }

        return new ArrayList<>(employeeMap.values());
    }




    @Override
    public Employee findById(Long id) {
    	
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        
        // Join with addresses if needed
        root.fetch("addresses", JoinType.LEFT); // Use LEFT JOIN to fetch addresses
        cq.select(root).where(cb.equal(root.get("emp_Id"), id));

        try {
            Employee employee = entityManager.createQuery(cq).getSingleResult();
            // No need to manually set addresses; they will be fetched due to the join
            return employee;
        } catch (NoResultException e) {
            throw new ResourceNotFoundException("Employee not exist with id: " + id);
        }
    }


    
    @Override
    public Employee save(Employee employee) {
        // Use EntityManager to persist the employee
        entityManager.persist(employee);
        return employee;
    }


    @Override
    public void update(Employee employee) {
        // Use the entity manager to merge the changes
        entityManager.merge(employee);
    }
    

    
    @Override
    public void deleteById(Long id) {
        // First, find the employee by ID
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            // Delete the associated addresses
        	addressDAO.deleteByEmployeeId(id);
            
            // Remove the employee
            entityManager.remove(employee);
        } else {
            throw new ResourceNotFoundException("Employee not exist with id: " + id);
        }
    }

    


}
