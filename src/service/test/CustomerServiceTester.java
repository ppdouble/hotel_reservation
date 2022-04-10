package service.test;

import model.Customer;
import service.CustomerService;

import java.util.Collection;

public class CustomerServiceTester {
    public static void main (String[] args) {
        CustomerService customerService = new CustomerService();

        System.out.println("Call addCustomer, getCustomer");
        customerService.addCustomer ("n@email.com", "fn", "ln");
        customerService.addCustomer ("l@email.com", "fnn", "lnn");

        System.out.println(customerService.getCustomer("n@email.com"));

        System.out.println("Call getAllCustomer");

        for (Customer customer : customerService.getAllCustomer()) {
            System.out.println(customer);
        }
    }
}
