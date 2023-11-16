package spring.data.jpa.springdatajpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.data.jpa.springdatajpa.entity.Customer;
import spring.data.jpa.springdatajpa.entity.Local;
import spring.data.jpa.springdatajpa.entity.Manager;
import spring.data.jpa.springdatajpa.entity.Order;

@SpringBootTest
public class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void saveLocal() {

        Manager manager = Manager.builder()
                .firstName("Mateo")
                .lastName("Perez")
                .build();

        Local local = Local.builder()
                .name("KFC")
                .floor("Second Floor")
                .manager(manager)
                .build();

        localRepository.save(local);

    }
    
    @Test
    public void findAllLocals() {
        List<Local> localList = localRepository.findAll();
        System.out.println("Local: " + localList);
    }
    
//    @Test
//    public void saveLocalWithOrders() {
//            Order order1 = Order.builder()
//                            .description("Entrada Pelicula 1 Sala 2 en 2D")
//                            .price(6.50)
//                            .build();
//            Order order2 = Order.builder()
//                            .description("Entrada Pelicula 2 Sala 3 en 3D")
//                            .price(8.50)
//                            .build();
//            Manager manager = Manager.builder()
//                            .firstName("Ramon")
//                            .lastName("Alvarez")
//                            .build();

//            Local local = Local.builder()
//                            .name("Cinema")
//                            .floor("Third Floor")
//                            .manager(manager)
//                            .orderList(List.of(order1, order2))
//                            .build();

//            localRepository.save(local);
//    }
    
    @Test
    public void findAllLocalsWithOrders() {
        List<Local> locals = localRepository.findAll();
        System.out.println("localList = " + locals);
    }
    
    @Test
    public void saveLocalWithCustomer() {
        Customer customer1 = Customer.builder()
                .firstName("Carl")
                .lastName("Jhonson")
                .email("carl@hotmail.com")
                .build();
        Customer customer2 = Customer.builder()
                .firstName("Eddie")
                .lastName("Pulaski")
                .email("eddie@hotmail.com")
                .build();

        Manager manager = Manager.builder()
                .firstName("Romina")
                .lastName("Alvarez")
                .build();

        Local local = Local.builder()
                .name("Clukin ' Bell")
                .floor("First Floor")
                .manager(manager)
                .customerList(List.of(customer1, customer2))
                .build();
        localRepository.save(local);
    }
    
    @Test
    public void findAllLocalsWithCustomers() {
        List<Local> localList = localRepository.findAll();
        System.out.println("localList = "+ localList);
    }

    @Test
    public void findCustomersByLocal() {
        Local local = localRepository.findById(10L).get();
        List<Customer> customerList = local.getCustomerList();
        System.out.println(customerList);

    }
}
