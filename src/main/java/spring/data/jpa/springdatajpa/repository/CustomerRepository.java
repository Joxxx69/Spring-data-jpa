package spring.data.jpa.springdatajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import spring.data.jpa.springdatajpa.entity.Customer;
import java.util.*;
import java.util.List;




@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
    
    //* ************** Repository ************* */
    Optional<Customer> findByFirstName(String firstName);
    
    List<Customer> findByFirstNameContaining(String firstName); // Containing --> sirve para filtrar de acuerdo a unas palabras

    List<Customer> findByLastNameNotNull();

    List<Customer> findByAddress_City(String city);

    //* ************** JPQL ************* */
    //@Query("select c from Customer c where c.email =?2") // ?2 me compara con el segundo parametro == email
    //Customer getCustomerByEmailAddress(String name, String email);
    @Query("select c from Customer c where c.email =?1") // ?1 me compara con el primer parametro == email
    Customer getCustomerByEmailAddress(String email);

    @Query("select c.firstName from Customer c where c.email = ?1 ")
    String getCustomerFirstNameByEmailAddress(String email);


    //* ************** SQL************ */
    @Query(
        value = "select * from tbl_customer where email_address = ?1",
        nativeQuery = true
    )
    Customer getCustomerByEmailAddressNative(String email);
    @Query(
        value = "select * from tbl_customer where email_address = :emailAddress",
        nativeQuery = true
    )
    Customer getCustomerByEmailAddressNativeNamedParam(@Param("emailAddress") String email);

    // a nivel de clase, repositorio, metodo
    @Transactional // habre una transaccion al modificar para garantizar la persistencia de datos
    
    // solo se ocupa en Repository
    @Modifying // permite avisar a sprign que voy a modificar(actualizacion o eliminacion) los datos 
    @Query(
        value = "update tbl_customer set first_name = ?1 where email_address = ?2",
        nativeQuery = true
    )
    void updateCustomerNameByEmail(String name, String email);
}
