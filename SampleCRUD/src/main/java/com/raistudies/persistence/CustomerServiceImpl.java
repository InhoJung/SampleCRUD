package com.raistudies.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raistudies.domain.Customer;

@Component
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void saveUser(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ".saveUser start");
		int resultCode = this.sqlSession.insert("com.raistudies.persistence.CustomerService.saveUser", customer);
		System.out.println("Result Code : " + resultCode);
	}

	@Override
	public void updateUser(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ".updateUser start. " + customer.getCustomer_id() + ", " + customer.getName() + ", " + customer.getStandard() + ", " + customer.getAge() + ", " + customer.getSex());
		System.out.println(customer);
		int resultCode = this.sqlSession.update("com.raistudies.persistence.CustomerService.updateUser", customer);
		System.out.println("Result Code : " + resultCode);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ".deleteUser start. " + id);
		int resultCode = this.sqlSession.delete("com.raistudies.persistence.CustomerService.deleteUser", id);
		System.out.println("Result Code : " + resultCode);
	}

	@Override
	public List<Customer> getAllUser() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName() + ".getAllUser start");
		List<Customer> list = this.sqlSession.selectList("com.raistudies.persistence.CustomerService.getAllUser");
		return list;
	}

}
