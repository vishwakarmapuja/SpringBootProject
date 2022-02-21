package net.javaguides.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;

@Service
public class ProductService{
	
	@Autowired ProductRepository repository;
	
	public Product saveProduct(Product customerdetail) {
		
		return repository.save(customerdetail);
	}
	public Optional<Product> getProductByPincode(Long cal_eligibility) {
		return repository.findById(cal_eligibility);
	}
	
	public boolean checkExistedProduct(Long pincode) {
		if(repository.existsById(pincode)) {
			return true;
		}
		return false;
	}
	
	public List<Product> getProductInfos(){
		return repository.findAll();
	}
	public Product updateProduct(Product product) {
		return repository.save(product);		
	}
	
	public void deleteProductById(long pincode) {
		repository.deleteById(null);
	}
	
	
}
