package com.sunglowsys.resource;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer result = customerService.save(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id) {
        Customer result = customerService.update(customer,id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
    @GetMapping("/customers")
    public ResponseEntity<Page<Customer>> getAll() {
        Page<Customer> result = customerService.findAll(PageRequest.of(0,10));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);


    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Optional<Customer>> getOne(@PathVariable("id") Long id) {
        Optional<Customer> result = customerService.findOne(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
