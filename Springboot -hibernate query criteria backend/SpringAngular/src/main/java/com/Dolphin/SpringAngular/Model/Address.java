package com.Dolphin.SpringAngular.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;

@Entity
@Table(name = "address") // Specify the table name
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    @Column(name = "id") // Specify the column name
	private Long Id;
	
	@Column(name = "street") // Specify the column name
	private String street;
	
	@Column(name = "city") // Specify the column name
	private String city;
	
	@Column(name = "state") // Specify the column name
	private String state;
	
	@JsonBackReference
    @ManyToOne // Many Addresses to One Employee
    @JoinColumn(name = "emp_Id") // Specify the foreign key column
	private Employee employee;
	 
	public Address() {
		
	}

	public Address(String street, String city, String state, Employee employee) {
		
		this.street = street;
		this.city = city;
		this.state = state;
		this.employee = employee;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	 
	 
	

}
