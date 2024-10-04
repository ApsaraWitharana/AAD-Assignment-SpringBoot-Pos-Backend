package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystem.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.util.AppUtil;
import lk.ijse.gdse68.springpossystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private Mapping mapping;
    @Autowired
    private CustomerDAO customerDAO;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

        customerDTO.setId(AppUtil.createCustomerId());
        var Customer = mapping.convertToEntity(customerDTO);
        var saveCustomer = customerDAO.save(Customer);
        System.out.println(customerDTO);

        if (saveCustomer == null){
            throw new DataPersistFailedException("Customer save Note found!");
        }

    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public CustomerResponse getSelectedCustomer(String id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return null;
    }
}
