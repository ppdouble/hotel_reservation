package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static Map<String, Customer> mapOfCustomer = new HashMap<String, Customer>();

    public void addCustomer (String email, String firstName, String lastName) {
        Customer customer = new Customer(email, firstName, lastName);
        mapOfCustomer.put(customer.getEmail(), customer);
    }

    /*
     * Retrieve Customer by Email
     */
    public Customer getCustomer (String customerEmail) {
        if (null == mapOfCustomer) {
            throw new IllegalArgumentException("System error. No Customer data model.");
        }
        if (mapOfCustomer.isEmpty() || !isEmailExists(customerEmail)) {
            throw new IllegalArgumentException("Error. Customer not exist. Please check email");
        }
        return mapOfCustomer.get(customerEmail);
    }

    public boolean isEmailExists (String customerEmail) {
        boolean res = true;
        if (mapOfCustomer.isEmpty() || !mapOfCustomer.containsKey(customerEmail)) {
            res = false;
        }
        return res;
    }

    public Collection<Customer> getAllCustomer () {
        return mapOfCustomer.values();
    }
}
