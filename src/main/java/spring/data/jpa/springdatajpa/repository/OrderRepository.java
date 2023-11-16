package spring.data.jpa.springdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.data.jpa.springdatajpa.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    Page<Order> findByDescriptionContaining(String description, Pageable pageable);
    
}
