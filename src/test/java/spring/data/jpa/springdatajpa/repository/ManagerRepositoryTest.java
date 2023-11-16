package spring.data.jpa.springdatajpa.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import spring.data.jpa.springdatajpa.entity.Manager;

@SpringBootTest
public class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void findAllManagers() {
        List<Manager> managerList = managerRepository.findAll();
        System.out.println("List: "+ managerList);
    }
}
