package academy.atl.customer.services;

import academy.atl.customer.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerServiceImpl() {

        customers.addAll(
                List.of(
                        Customer.builder()
                                .id(1)
                                .firstname("Fran")
                                .lastname("de oses")
                                .email("fdeo@asd.com")
                                .address("C/asd asdas ")
                                .build(),
                        Customer.builder()
                                .id(2)
                                .firstname("Pepe")
                                .lastname("Gacia")
                                .email("pep@asd.com")
                                .address("C/pe asdas ")
                                .build())
        );
    }

    public Customer getCustomer(Integer id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("El comprador no ha sido encontrado!!"));
    }

    public List<Customer> getAllCustomer() {
        return customers;
    }

    public void addCustomer(Customer customer) {

        customers.add(customer);

    }

    public void removeCustomer(Integer id) {

        customers.remove(
                customers.stream()
                        .filter(customer -> customer.getId().equals(id))
                        .findFirst()
                        .orElse(Customer.builder().build())
        );
    }

    public void updateCustomer(Integer id,
                               Customer customer) {
        for (Customer customer1 : customers) {
            if (customer1.getId().equals(id)) {
                customer1.setAddress(customer.getAddress());
                customer1.setLastname(customer.getLastname());
                customer1.setFirstname(customer.getFirstname());
                customer1.setEmail(customer.getEmail());
                break;
            }

        }
    }

    public List<Customer> searchCustomer(String email,
                                         String address) {
        return customers.stream()
                .filter(customer -> customer.getEmail().contains(email) || customer.getAddress().contains(address))
                .toList();

    }

}
