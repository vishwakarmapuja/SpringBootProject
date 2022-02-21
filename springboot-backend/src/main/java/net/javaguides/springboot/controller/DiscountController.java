package net.javaguides.springboot.controller;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import net.javaguides.springboot.model.OrderDiscount;
import net.javaguides.springboot.model.OrderRequest;
import net.javaguides.springboot.services.OrderDiscountService;


@RestController
public class DiscountController {
	public DiscountController(KieContainer kieContainer) {
	    }
	
    @Autowired
    private OrderDiscountService orderDiscountService;
 
    @PostMapping("/get-discount")
    public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
        OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
}
