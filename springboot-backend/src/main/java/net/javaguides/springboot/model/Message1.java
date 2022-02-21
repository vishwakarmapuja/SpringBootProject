package net.javaguides.springboot.model;

import java.util.ArrayList;
import java.util.List;

public class Message1 {
	private String message = "";
	private List<Product> customerdetial = new ArrayList<Product>();
	private String error = "";
	
	public Message1(String message, List<Product> customerdetail, String error) {
		this.message = message;
		this.customerdetial = customerdetail;
		this.error = error;
	}

	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Product> getCustomers(){
		return this.customerdetial;
	}
	
	public void setCustomers(ArrayList<Product> customers) {
		this.customerdetial = customers;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return this.error;
	}
}