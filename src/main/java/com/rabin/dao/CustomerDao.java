package com.rabin.dao;

import com.rabin.dto.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {


    public static void sleepExecution(int i ) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Customer> getCustomers() {

        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::sleepExecution)
                .peek(i-> System.out.println("processing count: "+ i))
                .mapToObj(i->new Customer(i,"customer"))
                .collect(Collectors.toList());

    }

}
