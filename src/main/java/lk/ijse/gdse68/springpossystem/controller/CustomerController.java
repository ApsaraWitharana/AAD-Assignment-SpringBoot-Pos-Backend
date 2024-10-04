package lk.ijse.gdse68.springpossystem.controller;

import lk.ijse.gdse68.springpossystem.dto.CustomerDTO;
import lk.ijse.gdse68.springpossystem.entity.Customer;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : sachini
 * @date : 2024-10-04
 **/
@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(Customer.class);

    //TODO:Customer CRUD Implement

    @PostMapping
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO){
        //TODO : Validate

        if (customerDTO.getId() == null || !customerDTO.getId().matches("^CUS-[0-9]{3}$")) {
            return new ResponseEntity<>("Customer ID is empty or invalid! It should match 'CUS-000' format.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getName() == null || !customerDTO.getName().matches("^[A-Za-z ]{4,}$")) {
            return new ResponseEntity<>("Customer Name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getAddress() == null || !customerDTO.getAddress().matches("^[A-Za-z0-9., -]{5,}$")) {
            return new ResponseEntity<>("Customer Address is empty or invalid! It should contain at least 5 alphanumeric characters.", HttpStatus.BAD_REQUEST);
        }

        if (customerDTO.getSalary() <= 0) {
            return new ResponseEntity<>("Customer Salary is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }

        //TODO :If all validations pass, save the customer
            try {
                customerService.saveCustomer(customerDTO);
                logger.info("connection initialized");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }catch (DataPersistFailedException e){
                return new ResponseEntity<>("Customer data could not be saved, data persistence failed.",HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>("Internal server error occurred while saving the customer.",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

