package com.Dolphin.SpringAngular.Model;

import java.util.List;

public class EmployeeDTO {
	
	private Long emp_Id;
    private String name;
    private String email_Id;
    private String designation;
    private int addresscount;
    private List<Address_view> addresses;
    
   
    
    public EmployeeDTO(Long emp_Id, String name, String email_Id, String designation, int addresscount, List<Address_view> addresses) {
        this.emp_Id = emp_Id;
        this.name = name;
        this.email_Id = email_Id;
        this.designation = designation;
        this.addresscount = addresscount;
        this.addresses = addresses;
    }
    
    

	public EmployeeDTO() {
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

	public int getAddresscount() {
		return addresscount;
	}

	public void setAddresscount(int addresscount) {
		this.addresscount = addresscount;
	}

	public List<Address_view> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address_view> addresses) {
		this.addresses = addresses;
	}
    
    

}
