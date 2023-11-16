package spring.data.jpa.springdatajpa.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "manager")// sirve en conjunto con FetchType.LAZY para excluir
public class Local {
    @Id
    @SequenceGenerator(
        name = "local_sequence",
        sequenceName = "local_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "local_sequence",
        strategy = GenerationType.SEQUENCE
    )
    private Long localId;
    private String name;
    private String floor;

    @OneToOne(// por defecto en eager
        cascade = CascadeType.PERSIST,
        //fetch = FetchType.LAZY, // solo para obtener los registros de la entidad local
        fetch = FetchType.EAGER, // para obtener los resgistros de la entidad local y manager
        optional = false //tengo que mandar deley al manager
    )
    @JoinColumn(
        name = "manager_id",
        referencedColumnName = "managerId"
    )
    private Manager manager;

    @ManyToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    @JoinTable( // creacion de una tabla intermediaria - para las foreing keys de las tablas
        name = "local_customer_map",
        joinColumns = @JoinColumn(  //desde esta entidad - Local
                name="local_id",
                referencedColumnName="localId"    
            ),
            inverseJoinColumns = @JoinColumn( //desde la otra entidad - Customer
                name="customer_id",
                referencedColumnName = "customerId"
            )
    )
    private List<Customer> customerList;




/* 
 - Operacion One to Many
 * estamos instanciando desde la entidad padre
 */
    //@OneToMany( // por defecto en lazy
    //    cascade = CascadeType.ALL,
    //    fetch = FetchType.EAGER
    //)
    //@JoinColumn(
    //    name = "local_id",
    //    referencedColumnName = "localId"
    //)
    //private List<Order> orderList;
}
