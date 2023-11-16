package spring.data.jpa.springdatajpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import spring.data.jpa.springdatajpa.entity.Local;
import spring.data.jpa.springdatajpa.entity.Manager;
import spring.data.jpa.springdatajpa.entity.Order;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void orders() {
        List<Order> orders = orderRepository.findAll();
        System.out.println("Orders = "+ orders);
    }

    @Test
    public void saveOrder() {
        Manager manager = Manager.builder()
                .firstName("Paula")
                .lastName("Castillo")
                .build();

        Local local = Local.builder()
                .name("Binco")
                .floor("Fourth Floor")
                .manager(manager)
                .build();

        Order order1 = Order.builder()
                .description("Camisa de tirantes blanca")
                .price(10.0)
                .local(local)
                .build();
        orderRepository.save(order1);
    }

    //- Paginado 
    @Test
    public void findAllOrdersPaging() {
        Pageable firstPageWiithThreeRecords = PageRequest.of(0, 1);

        List<Order> orderList = orderRepository.findAll(firstPageWiithThreeRecords).getContent();
        Long totalElements = orderRepository.findAll(firstPageWiithThreeRecords).getTotalElements();
        int totalPages = orderRepository.findAll(firstPageWiithThreeRecords).getTotalPages();

        System.out.println("Total de paginas = " + totalPages);
        System.out.println("total de Elementos = " + totalElements);
        System.out.println("orderList = " + orderList);

    }
    
    //-Ordenamiento


    @Test
    public void findAllOrdersWithSorting() {
        Pageable sortByPrice = PageRequest.of(0, 3, Sort.by("price"));
        Pageable sortByPriceDesc = PageRequest.of(0, 3, Sort.by("price").descending());
        List<Order> orders = orderRepository.findAll(sortByPriceDesc).getContent();

        System.out.println("Orden por precio = " + orders);
    }
    
    @Test
    public void findAllOrdersDescriptionContaining() {
        Pageable pageable1 = PageRequest.of(0, 1);
        Pageable pageable2 = PageRequest.of(1, 1);
        List<Order> orderList = orderRepository.findByDescriptionContaining("En", pageable1).getContent();
        List<Order> orderList2 = orderRepository.findByDescriptionContaining("En", pageable2).getContent();
        System.out.println("Pagina 1 = " + orderList);
        System.out.println("*********************************************************");
        System.out.println("Pagina 2 = "+ orderList2);
    }

}
