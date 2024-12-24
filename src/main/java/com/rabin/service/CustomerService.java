package com.rabin.service;

import com.rabin.dao.CustomerDao;
import com.rabin.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers(){

        long start = System.currentTimeMillis();

        List<Customer> customers = customerDao.getCustomers();

        long end = System.currentTimeMillis();

        System.out.println("Total execution time "+ (end-start));
        return customers;
    }

}
