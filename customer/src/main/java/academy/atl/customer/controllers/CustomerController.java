package academy.atl.customer.controllers;

import academy.atl.customer.entities.Customer;
import academy.atl.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return service.getCustomer(id);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomer() {
        return service.getAllCustomer();
    }


    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer) {

        service.addCustomer(customer);

    }

    @DeleteMapping("/customer/{id}")
    public void removeCustomer(@PathVariable Integer id) {

        service.removeCustomer(id);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        service.updateCustomer(id, customer);
    }

    @GetMapping("/customer/search")
    public List<Customer> searchCustomer(@RequestParam(name = "email", required = false) String email,
                                         @RequestParam(name = "address", required = false) String address) {
        return service.searchCustomer(email, address);

    }
}
