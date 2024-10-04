package lk.ijse.gdse68.springpossystem.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author : sachini
 * @date : 2024-10-04
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO{
    private String id;
    private String name;
    private String address;
    private Double salary;
}
