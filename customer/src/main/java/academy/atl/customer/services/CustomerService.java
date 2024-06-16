package academy.atl.customer.services;

import academy.atl.customer.entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomer(Integer id);

    List<Customer> getAllCustomer();

    void addCustomer(Customer customer);

    void removeCustomer(Integer id);

    void updateCustomer(Integer id, Customer customer);

    List<Customer> searchCustomer(String email, String address);

    default void hacerAlgo() {
        System.out.println("Hacer algo..");
    }
}
