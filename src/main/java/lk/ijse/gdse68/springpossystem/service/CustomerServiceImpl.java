package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerErrorResponse;
import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystem.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystem.entity.Customer;
import lk.ijse.gdse68.springpossystem.exception.CustomerNoteFound;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.util.AppUtil;
import lk.ijse.gdse68.springpossystem.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    Logger logger = LoggerFactory.getLogger(Customer.class);

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
        logger.info("Fetching customer with ID: " + id); // Add logging
        if (customerDAO.existsById(id)) {
            Customer customerEntityById = customerDAO.getCustomerEntityById(id);
            if (customerEntityById == null) {
                logger.warn("No customer found for ID: " + id);
                return new CustomerErrorResponse(0, "Customer not found!");
            }
            return (CustomerResponse) mapping.convertToDTO(customerEntityById); // Ensure this returns the correct type
        } else {
            logger.warn("Customer does not exist with ID: " + id);
            return new CustomerErrorResponse(0, "Customer not found!");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
       List<Customer> customers = customerDAO.findAll();
       List<CustomerDTO> customerDTOS = new ArrayList<>();
       for (Customer customer: customers){
           CustomerDTO customerDTO = new CustomerDTO();
           customerDTO.setId(customer.getId());
           customerDTO.setName(customer.getName());
           customerDTO.setAddress(customer.getAddress());
           customerDTO.setSalary(customer.getSalary());
           customerDTOS.add(customerDTO);
       }
       return customerDTOS;
    }
}
