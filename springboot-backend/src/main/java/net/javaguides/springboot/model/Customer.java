package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "customer")
	public class Customer {
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
			
		private long id;
		
		@Column
		private String mobileno;
		
		@Column
		private String customer_ID;
		
		@Column
		private String url_path;
		
		
		public String getUrl_path() {
			return url_path;
		}
		public String getCustomer_ID() {
			return customer_ID;
		}

		public void setCustomer_ID(String customer_ID) {
			this.customer_ID = customer_ID;
		}

		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		public String getMobileno() {
			return mobileno;
		}
		public void setMobileno(String mobileno) {
			this.mobileno = mobileno;
		}
		
		public Customer() {}
		
		public Customer(String mobileno,String otp,String customer_ID,String url_path) {
			this.mobileno = mobileno;
			this.customer_ID= customer_ID;
			this.url_path=url_path;
			
		}
	
		public String toString() {
			return String.format("id=%d, mobileno='%s',customer_ID='%s',url_path='%s'",
									id, mobileno,customer_ID,url_path);	
		}

		
	}
	
