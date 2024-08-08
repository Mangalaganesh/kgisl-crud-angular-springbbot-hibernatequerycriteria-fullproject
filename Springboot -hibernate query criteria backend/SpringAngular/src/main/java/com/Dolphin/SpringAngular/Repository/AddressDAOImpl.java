package com.Dolphin.SpringAngular.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.Dolphin.SpringAngular.DAO.AddressDAO;
import com.Dolphin.SpringAngular.Model.Address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.springframework.jdbc.core.RowMapper;

@Repository
public class AddressDAOImpl implements AddressDAO {


	
	 @Autowired
	 private EntityManager entityManager;


	 
	    @Override
	    public List<Address> findByEmployeeId(Long employeeId) {
	        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Address> cq = cb.createQuery(Address.class);
	        Root<Address> root = cq.from(Address.class);
	        cq.select(root).where(cb.equal(root.get("employee").get("emp_Id"), employeeId));
	        return entityManager.createQuery(cq).getResultList();
	    }
	   



	    
	    @Override
	    public void save(Address address) {
	        entityManager.persist(address);
	    }



	
	    @Override
	    public void deleteByEmployeeId(Long employeeId) {
	    	
	        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaDelete<Address> delete = cb.createCriteriaDelete(Address.class);
	        Root<Address> root = delete.from(Address.class);
	        
	        delete.where(cb.equal(root.get("employee").get("emp_Id"), employeeId));

	        entityManager.createQuery(delete).executeUpdate();
	    }

	


	
	



}
