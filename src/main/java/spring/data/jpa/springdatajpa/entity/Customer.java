package spring.data.jpa.springdatajpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
    name = "tbl_customer",
    uniqueConstraints = @UniqueConstraint(
        name="email_unique",
        columnNames = "email_address"    
    )
)
public class Customer {
    @Id
    @SequenceGenerator(
        name = "customer_sequence", // se ocupara para el codigo 
        sequenceName = "customer_sequence",// de ocupa para la db
        allocationSize =1                  // reserva, para evitar solicitudes 
    )
    @GeneratedValue(
        generator = "customer_sequence",
        strategy = GenerationType.SEQUENCE

    )
    private long customerId;
    private String firstName;
    private String lastName;

    @Column(
        name = "email_address",
        nullable = false
    )
    private String email;
    
    //private String customerCity;
    //private String customerMainStreet;
    //private String customerSecondaryStreet;

    // se coloca en el campo de una entidad para indicar que es la instancia 
    //de otra clase marcada con @Embeddable
    @Embedded
    private Address address;
    
}