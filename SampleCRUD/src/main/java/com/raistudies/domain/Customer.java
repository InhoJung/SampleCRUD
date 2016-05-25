package com.raistudies.domain;

import java.io.Serializable;


public class Customer implements Serializable{
	
	private static final long serialVersionUID = 3647233284813657927L;
	
	private String customer_id;
	private String name = null;
	private String standard = null;
	private String age;
	private String sex = null;
	

	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "Customer [customer_id " + customer_id + ", name=" + name + ", standard=" + standard + ", age=" + age
				+ ", sex=" + sex + "]";
	}
}
