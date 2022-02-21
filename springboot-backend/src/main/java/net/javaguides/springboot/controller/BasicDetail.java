package net.javaguides.springboot.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.javaguides.springboot.model.Message1;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.services.ProductService;    
@CrossOrigin("*")
@RestController
@RequestMapping("/api/customerdetial/")
public class BasicDetail{	
	
	private  static final String PATH="error";
	@Autowired
	ProductService productService;
	@PostMapping("/create")
	public ResponseEntity<Message1> addNewCustomer(@RequestBody Product customerdetail,HttpServletRequest request, HttpServletResponse response) {
		try {
			
				HttpSession session = request.getSession();
				String customer_ID=(String)session.getAttribute("customer_ID");
				// System.out.println(customer_ID);
				
				customerdetail.setCustomer_id(customer_ID);
				//System.out.println(customerdetail.customer_id);
			
			Product returnedcustomer =  productService.saveProduct(customerdetail);
			
			return new ResponseEntity<Message1>(new Message1("Upload Successfully!", 
											Arrays.asList(returnedcustomer), ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message1>(new Message1("Fail to post a new CustomerDetail!", 
											null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	
	
	
	
	@PostMapping("/retrieveinfos")
	public ResponseEntity<Message1> retrieveProductInfo(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Product> customerInfos = productService.getProductInfos();

		    response.setContentType("application/json");

		    String customer_id = request.getParameter("customer_id");
			
			return new ResponseEntity<Message1>(new Message1("Get Customers' Infos!", 
												customerInfos, ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message1>(new Message1("Fail!",
												null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{pincode}")
	public ResponseEntity<Message1> getCustomerById(@PathVariable Long pincode) {
		try {
			Optional<Product> optCustomer = productService.getProductByPincode(pincode);
			
			if(optCustomer.isPresent()) {
				return new ResponseEntity<Message1>(new Message1("Successfully! Retrieve a ccustomerdetail by pincode = " + pincode,
															Arrays.asList(optCustomer.get()), ""), HttpStatus.OK);
			} else {
				return new ResponseEntity<Message1>(new Message1("Failure -> NOT Found a customer by id = " +pincode,
						null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message1>(new Message1("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Message1> updateProductByPincode(@RequestBody Product _customer, 
																	@PathVariable Long id) {
		try {
			if(productService.checkExistedProduct(id)) {
				Product customerdetail = productService.getProductByPincode(id).get();
				
				//set new values for customer
				/*customer.setFirstname(_customer.getFirstname());
				customer.setLastname(_customer.getLastname());
				customer.setAddress(customer.getAddress());
				customer.setAge(_customer.getAge());
				 	*/
				customerdetail.setPincode(_customer.getPincode());
				customerdetail.setincome(_customer.getincome());
				customerdetail.setEmi(_customer.getEmi());
				customerdetail.setEmployment_type(_customer.getEmployment_type());
				customerdetail.setdob(_customer.getdob());
			
				// save the change to database
				productService.updateProduct(customerdetail);
				
				return new ResponseEntity<Message1>(new Message1("Successfully! Updated a Customer "
																		+ "with id = " + id,
																	Arrays.asList(customerdetail), ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message1>(new Message1("Failer! Can NOT Found a Customer "
						+ "with id = " + id,
					null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message1>(new Message1("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Message1> deleteProductById(@PathVariable long id) {
		try {
			// checking the existed of a Customer with id
			if(productService.checkExistedProduct(id)) {
				productService.deleteProductById(id);
				
				return new ResponseEntity<Message1> (new Message1("Successfully! Delete a Customer with pimcode = " + id, 
														null, ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message1>(new Message1("Failer! Can NOT Found a Customer "
														+ "with id = " + id, null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message1>(new Message1("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public String getErrorPath(){
		return PATH;
	}
	
@PostMapping("/cal")
public double[] Calculation(HttpServletRequest request, HttpServletResponse response) {
	
	int loanAmount = Integer.parseInt(request.getParameter("cal"));
	int interestRate=Integer.parseInt(request.getParameter("Interest"));
	int loanDurationYears=Integer.parseInt(request.getParameter("Tenure"));
	
		if(interestRate==' ')
		{
			interestRate =13;
		}
		if(loanDurationYears==' ')
		{
			 loanDurationYears =3;
		}
		
		 double  monthlyPayment, totalInterest;	  
	   monthlyPayment = payment(loanAmount, loanDurationYears, interestRate);
	    totalInterest = ((monthlyPayment * (loanDurationYears*12))-loanAmount);
	    printTotals(loanAmount, loanDurationYears, interestRate, monthlyPayment, totalInterest);
	double[] array= {monthlyPayment,totalInterest};
	 //  return Math.round(totalInterest);
	   return array;
	  }

	  public static double payment(int loanAmount, int loanDurationYears, double interestRate)
	  {
		     
	      int a = loanAmount;
	      int n = (loanDurationYears*12);//int y = ((loanDurationYears*12)/12); for month
	      double i = ((interestRate*.01)/12);
	      double monthlyPayment = a*(Math.pow((1+i),n)*i)/((Math.pow((1+i),n))-1);
	      //double monthlyPayment = a*(Math.pow((1+i),y)*i)/((Math.pow((1+i),y))-1); for month
	      return monthlyPayment;
	  }
	  public static void printTotals(int loanAmount, int loanDurationYears, double interestRate, double monthlyPayment, double totalInterest)
	  {
	      System.out.println("For a " + loanDurationYears + " year loan of $" + loanAmount + " at " + interestRate + "% interest:");
	      System.out.printf("Monthly payment = $"+ "%.2f", monthlyPayment);
	      System.out.println();
	      System.out.printf("Total interest = $"+ "%.2f", totalInterest);
	      System.out.println();
	  }
	 
	
	}	

	
