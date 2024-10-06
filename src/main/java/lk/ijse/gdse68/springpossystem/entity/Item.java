package lk.ijse.gdse68.springpossystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : sachini
 * @date : 2024-10-06
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "spring_pos_system")
public class Item {
    @Id
    private String code;
    private String name;
    private BigDecimal price;
    private int qty;
}
