package net.javaguides.springboot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;
import java.util.Arrays;

import java.util.List;

import java.util.Optional;
import java.util.Random;
import javax.servlet.ServletException;
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
import net.javaguides.springboot.model.Customer;
import net.javaguides.springboot.model.Message;
import net.javaguides.springboot.services.CustomerServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer/")
public class CustomerController{	
	private  static final String PATH="error";
	@Autowired
	CustomerServices customerServices;

	@PostMapping("/create")
	public ResponseEntity<Message> addNewCustomer(@RequestBody Customer customer,HttpServletRequest request, HttpServletResponse response,String mobileno) {
		
		try {
			//String Mobileno = request.getParameter("mobileno");
			// System.out.println(customerServices.CheckExistsMobile(mobileno));
			  
		Customer returnedCustomer = customerServices.saveCustomer(customer);
			
		//System.out.println(returnedCustomer.getCustomer_ID());
		  HttpSession session = request.getSession();
	        session.setAttribute("customer_ID",returnedCustomer.getCustomer_ID());
	        //String custid = (String)session.getAttribute("customer_ID");
	       // System.out.println(custid);
	    	        
		return	new ResponseEntity<Message>(new Message("Upload Successfully!", 
										Arrays.asList(returnedCustomer), ""), HttpStatus.OK);
		
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail to post a new Customer!", 
										null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	
	}

	
	@GetMapping("/retrieveinfos")
	public ResponseEntity<Message> retrieveCustomerInfo() {
		
		try {
			List<Customer> customerInfos = customerServices.getCustomerInfos();
			
			return new ResponseEntity<Message>(new Message("Get Customers' Infos!", 
												customerInfos, ""), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Fail!",
												null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findone/{id}")
	public ResponseEntity<Message> getCustomerById(@PathVariable long id) {
		try {
			Optional<Customer> optCustomer = customerServices.getCustomerById(id);
			
			if(optCustomer.isPresent()) {
				return new ResponseEntity<Message>(new Message("Successfully! Retrieve a customer by id = " + id,
															Arrays.asList(optCustomer.get()), ""), HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(new Message("Failure -> NOT Found a customer by id = " + id,
						null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<Message> updateCustomerById(@RequestBody Customer _customer, 
																	@PathVariable long id) {
		try {
			if(customerServices.checkExistedCustomer(id)) {
				Customer customer = customerServices.getCustomerById(id).get();
				
				//set new values for customer
				/*customer.setFirstname(_customer.getFirstname());
				customer.setLastname(_customer.getLastname());
				customer.setAddress(customer.getAddress());
				customer.setAge(_customer.getAge());
				 	*/
				customer.setMobileno(_customer.getMobileno());
				// save the change to database
				customerServices.updateCustomer(customer);
				
				return new ResponseEntity<Message>(new Message("Successfully! Updated a Customer "
																		+ "with id = " + id,
																	Arrays.asList(customer), ""), HttpStatus.OK);}
			else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Customer "
						+ "with id = " + id,
					null, ""), HttpStatus.NOT_FOUND);
		
			}
			}
		catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Message> deleteCustomerById(@PathVariable long id) {
		try {
			// checking the existed of a Customer with id
			if(customerServices.checkExistedCustomer(id)) {
				customerServices.deleteCustomerById(id);
				
				return new ResponseEntity<Message> (new Message("Successfully! Delete a Customer with id = " + id, 
														null, ""), HttpStatus.OK);
			}else {
				return new ResponseEntity<Message>(new Message("Failer! Can NOT Found a Customer "
														+ "with id = " + id, null, ""), HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return new ResponseEntity<Message>(new Message("Failure",
					null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public String getErrorPath(){
		return PATH;
	}
	
	@PostMapping("/send")	
	protected String doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	    response.setContentType("application/json");

	    String mobileno = request.getParameter("mobileno"); //Null value
	    Random rand = new Random();
	    int otp =rand.nextInt(1000,10000);
	    HttpSession session = request.getSession();
        session.setAttribute("otp",otp);
       //System.out.println (session.getAttribute("otp"));
	//	$this->session->set_tempdata('OTP', $OTP, 600);
		String message="Your one time login password for Finaleap is "+otp+". This password will be valid for 10 min";
	//	String messages ="urlencode($message)";
		String sender = "FINALP"; 
		String apikey = "975cgeoe8x043trf759q7160r99060j02l";
		String baseurl = "https://instantalerts.co/api/web/send?apikey="+apikey;
		
		message=URLEncoder.encode(message, "UTF-8");	
		URL url = new URL(baseurl+"&sender="+sender+"&to="+mobileno+"&message="+message);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.getOutputStream();
		con.getInputStream();
		BufferedReader rd;
		String line;
		String result = "";
		rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		while ((line = rd.readLine()) != null)
		{
			result += line;
		}
		rd.close(); 
		//System.out.println("Result is" + result);
		//System.out.println (session.getAttribute("otp"));
		return result;				
	}
	@PostMapping("/verify_otp")	
	protected String verify_otp(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	    throws ServletException, IOException {	
		 response.setContentType("application/json");
		int send_otp = Integer.parseInt(request.getParameter("send_otp"));
		//System.out.println(send_otp);
		 int Verify_otp=(Integer)session.getAttribute("otp");
		 
		 //System.out.println(Verify_otp);
		 if(send_otp==Verify_otp)
		 {
			 //System.out.println("Success Otp Match");	 
			 //session.setAttribute("result","success");
			 String result="success";
			 return result;
			
		 }
		 else
		 {
			// System.out.println("not match");
			// session.setAttribute("result","fail");
			 return "fail";
		 }			
	}
	
	
}