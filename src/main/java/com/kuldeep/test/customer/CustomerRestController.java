package com.kuldeep.test.customer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@RestController
public class CustomerRestController {
    private final CustomerRepository repository;

    public CustomerRestController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerById(@PathVariable Long id){
        return this.repository.findById(id).get();
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Customer> getAllCustomers(){
        return this.repository.findAll();
    }
}
