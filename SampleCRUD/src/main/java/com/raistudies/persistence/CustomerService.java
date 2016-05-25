package com.raistudies.persistence;

import java.util.List;

import com.raistudies.domain.Customer;;

public interface CustomerService {
	
	public void saveUser(Customer customer);
	public void updateUser(Customer customer);
	public void deleteUser(String id);
	public List<Customer> getAllUser();
}
