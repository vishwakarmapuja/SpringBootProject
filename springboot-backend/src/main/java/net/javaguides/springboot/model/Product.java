package net.javaguides.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "customerdetial")

public class Product {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
		
	private long id;
	
	@Column
	private String pincode;
	@Column
	private String employment_type;
	@Column
	private String income;
	@Column
	private String emi;
	@Column
	private String cal_eligibility;
	

	@Column
	private String customer_id;
	
	
	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCal_eligibility() {
		return cal_eligibility;
	}

	public void setCal_eligibility(String cal_eligibility) {
		this.cal_eligibility = cal_eligibility;
	}
	@Column
	private String pancard;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
	
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	
	public Date getdob() {
		return dob;
	}

	public void setdob(Date dob) {
		this.dob=dob;
	}
	
	public String getEmployment_type() {
		return employment_type;
	}
	

	public void setEmployment_type(String employment_type) {
		this.employment_type = employment_type;
	}

	public String getincome() {
		return income;
	}

	public void setincome(String income) {
		this.income = income;
	}

	public String getEmi() {
		return emi;
	}

	public void setEmi(String emi) {
		this.emi = emi;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public Product() {}

	public Product(String pincode, String employment_type, String income, String emi, String pancard,Date dob ,String cal_eligibility,String customer_id) {
		this.pincode = pincode;
		this.employment_type = employment_type;
		this.income = income;
		this.emi = emi;
		this.pancard = pancard;
		this.dob = dob;
		this.cal_eligibility = cal_eligibility;
		this.customer_id= customer_id;
	}
	public String toString() {
		return String.format("pincode='%s',employment_type='%s',income='%s', emi='%s',pancard='%s',dob='%s',cal_eligibility='%s',customer_id='%s'",
				pincode,employment_type,income,emi,pancard,dob,cal_eligibility,customer_id);	
	}
}
