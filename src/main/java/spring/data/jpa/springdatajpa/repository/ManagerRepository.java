package spring.data.jpa.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.data.jpa.springdatajpa.entity.Manager;

import java.lang.Long;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    
    
}
