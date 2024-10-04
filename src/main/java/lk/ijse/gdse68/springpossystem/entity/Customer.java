package lk.ijse.gdse68.springpossystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "spring_pos_system")
public class Customer {
    @Id
    @Column(name = "id", length = 50)
    private String cusId;
    @Column(name = "customer_name", length = 50)
    private String name;
    @Column(name = "customer_address", length = 50)
    private String address;
    @Column(name = "customer_salary")
    private Double salary;
}
