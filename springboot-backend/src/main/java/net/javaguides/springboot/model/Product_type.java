package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	
	@Entity
	@Table(name = "product")
	public class Product_type {
		@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
			
		private long id;
		
		@Column
		private String product_type;
		
		public Product_type(String product_type, String customer_ID) {
			super();
			this.product_type = product_type;
			this.customer_ID = customer_ID;
		}
		@Column
		private String customer_ID;
		
		public String getProduct_type() {
			return product_type;
		}

		public void setProduct_type(String product_type) {
			this.product_type = product_type;
		}


		public String getCustomer_ID() {
			return customer_ID;
		}

		public void setCustomer_ID(String customer_ID) {
			this.customer_ID = customer_ID;
		}
		public Product_type() {}
		public String toString() {
			return String.format("id=%d,customer_ID ='%s',product_type='%s'",
									id, customer_ID,product_type);	
		}


	}

