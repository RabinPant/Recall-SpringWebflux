package com.rabin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DataController {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @PostMapping("/customer/create")
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        return reactiveMongoTemplate.save(customer);
    }

    @GetMapping("/customer/all")
    public Flux<Customer> getAllCustomer(){
        return reactiveMongoTemplate.findAll(Customer.class);
    }

    @GetMapping("/employee/{id}")
    public Mono<Customer> findByCustomerId(@PathVariable("id") String Id){

        return reactiveMongoTemplate.findById(Id, Customer.class);
    }


    private Mono<Customer> getCustomerById(String Id){
        Criteria criteria = Criteria.where("id").is(Id);
        Query query = Query.query(criteria);
        return reactiveMongoTemplate.findOne(query, Customer.class);
    }

}
