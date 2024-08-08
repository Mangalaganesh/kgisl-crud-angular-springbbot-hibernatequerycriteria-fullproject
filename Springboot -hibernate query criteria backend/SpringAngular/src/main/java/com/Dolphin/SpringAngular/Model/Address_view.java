package com.Dolphin.SpringAngular.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_with_addresses")
public class Address_view {
	
	@Id 
	private Long address_id;
	
    private String street;
    private String city;
    private String state;
    
    private Long emp_Id;

	public Address_view() {
		super();
	}

	public Address_view(Long address_id, String street, String city, String state) {
		super();
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getEmp_Id() {
		return emp_Id;
	}

	public void setEmp_Id(Long emp_Id) {
		this.emp_Id = emp_Id;
	}
	
	

}
