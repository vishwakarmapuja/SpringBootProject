package net.javaguides.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Customer;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.CustomerRepository;


@Service
public class CustomerServices{
	
	@Autowired CustomerRepository repository;
	
	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}
	
	public List<Customer> getCustomerInfos(){
		return repository.findAll();
	}
	
	public Optional<Customer> getCustomerById(long id) {
		return repository.findById(id);
	}
	
	public boolean checkExistedCustomer(long id) {
		//List<Customer> findDistinctMobileno(String mobileno1);
	//	return (List<Customer>) repository.findByMobileno(mobileno);
		if(repository.equals(id)) {
			return true;	
		}
		else
		{
			return false;
		}
		
	//	Example<Customer> example = Example.of(new User(firstName, lastName, username));
	//return  repository.findAll(mobileno);
		//return false;
		//return false;
		

	}
	
	public Customer updateCustomer(Customer customer) {
		return repository.save(customer);		
	}
	
	public void deleteCustomerById(long id) {
		repository.deleteById(id);
	}

	public List<Customer> postCustomerInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
