package spring.data.jpa.springdatajpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.data.jpa.springdatajpa.entity.Address;
import spring.data.jpa.springdatajpa.entity.Customer;

@SpringBootTest

public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveCustomer() {
        Customer customer = Customer.builder()
                .firstName("jose")
                .lastName("Gualotuna")
                .email("jose@hotmail.com")
                .build();

        customerRepository.save(customer);
    }

    @Test
    public void findCustomerByFirstName() {
        Customer customer = customerRepository.findByFirstName("jose").get();
        System.out.println(customer);
    }

    @Test
    public void findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer : customerList) {
            System.out.println(customer.hashCode() + " : " + customer.getFirstName());

        }
    }

    @Test
    public void saveCustomerWithAddressEmbedded() {

        Address address = Address.builder()
                .city("Quito")
                .mainStreet("Quito Av. 6 de Diciembre")
                .secondaryStreet("Av. Colon")
                .build();

        Customer customer = Customer.builder()
                .firstName("Pablo")
                .lastName("Ramirez")
                .email("pablo@hotmail.com")
                .address(address)
                .build();

        customerRepository.save(customer);
    }

    @Test
    public void findByFirstNameContaining() {
        List<Customer> customers = customerRepository.findByFirstNameContaining("Pa");
        System.out.println(customers);
    }
    
    @Test
    public void findByLastNameNotNull() {
        List<Customer> customers = customerRepository.findByLastNameNotNull();
        System.out.println(customers);
    }
    @Test
    public void findAllCustomersByAddressCity() {
        List<Customer> customers = customerRepository.findByAddress_City("quito");
        System.out.println(customers);
    }
    @Test
    public void getCustomerByEmailAddress() {
        Customer customer = customerRepository.getCustomerByEmailAddress("santiago@hotmail.com");
        System.out.println("Customer: " + customer);

    }
    
    @Test
    public void getCustomerFirstNameByEmailAddress() {
        String firstName = customerRepository.getCustomerFirstNameByEmailAddress("santiago@hotmail.com");
        System.out.println("Name: " + firstName);
    }
    
    @Test
    public void getCustomerByEmailAddressNative() {
        Customer customer = customerRepository.getCustomerByEmailAddressNative("paula@hotmail.com");
        System.out.println("Customer: " + customer);
    }
    @Test
    public void getCustomerByEmailAddressNativeParam() {
        Customer customer = customerRepository.getCustomerByEmailAddressNativeNamedParam("pablo@hotmail.com");
        System.out.println("Customer: " + customer);
    }
    
    @Test
    public void updateCustomerNameByEmail() {
        customerRepository.updateCustomerNameByEmail("Renato", "pablo@hotmail.com");
    }
}
