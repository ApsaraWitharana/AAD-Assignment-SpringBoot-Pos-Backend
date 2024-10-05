package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystem.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystem.entity.Customer;
import lk.ijse.gdse68.springpossystem.exception.CustomerNoteFound;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.util.AppUtil;
import lk.ijse.gdse68.springpossystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : sachini
 * @date : 2024-10-04
 **/
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

        Optional<Customer> tmpCustomerEntity = customerDAO.findById(id);
        if (!tmpCustomerEntity.isPresent()){
            throw new CustomerNoteFound("Customer update not found!");

        }else {
            Customer customer = tmpCustomerEntity.get();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customer.setSalary(customerDTO.getSalary());
            customerDAO.save(customer); // Save the updated entity back to the database

        }
    }

    @Override
    public void deleteCustomer(String id) {

        Optional<Customer> findId = customerDAO.findById(id);
            if (!findId.isPresent()){
                throw new CustomerNoteFound("Customer not found!");
            }else {
                // If the customer is found, proceed with the deletion
                customerDAO.deleteById(id);
            }
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
