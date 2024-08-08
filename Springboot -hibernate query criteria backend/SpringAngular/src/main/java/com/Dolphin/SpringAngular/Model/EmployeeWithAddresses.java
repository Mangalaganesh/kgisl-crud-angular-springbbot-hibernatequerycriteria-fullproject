package com.Dolphin.SpringAngular.Model;

import java.util.List;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "employee_with_addresses")
public class EmployeeWithAddresses {
	
	@Id
    private Long emp_Id;
    private String name;
    private String email_Id;
    private String designation;
    
  
   

    @OneToMany // Update the relationship here
	@JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id", insertable = false, updatable = false)
    private List<Address_view> addresses;
    
 // Constructor with parameters
    public EmployeeWithAddresses(Long emp_Id, String name, String email_Id, String designation, List<Address_view> addresses) {
        this.emp_Id = emp_Id;
        this.name = name;
        this.email_Id = email_Id;
        this.designation = designation;
        this.addresses = addresses;
      
    }
    
    

	public EmployeeWithAddresses() {
	super();
}



	public Long getEmp_Id() {
		return emp_Id;
	}

	public void setEmp_Id(Long emp_Id) {
		this.emp_Id = emp_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Address_view> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address_view> addresses) {
		this.addresses = addresses;
	}



	
	

}
