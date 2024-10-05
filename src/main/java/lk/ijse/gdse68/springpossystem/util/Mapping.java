package lk.ijse.gdse68.springpossystem.util;

import lk.ijse.gdse68.springpossystem.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystem.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-04
 **/
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDTO(Customer customer){
        return modelMapper.map(customer,CustomerDTO.class);
    }

    public Customer convertToEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO,Customer.class);
    }
}
