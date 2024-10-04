package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dto.CustomerDTO;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-10-04
 **/
public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String id,CustomerDTO customerDTO);
    void deleteCustomer(String id);
   CustomerResponse getSelectedCustomer(String id);

    List<CustomerDTO> getAllCustomers();
}
