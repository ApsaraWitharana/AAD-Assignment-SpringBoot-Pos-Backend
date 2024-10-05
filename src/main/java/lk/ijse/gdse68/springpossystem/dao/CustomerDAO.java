package lk.ijse.gdse68.springpossystem.dao;

import lk.ijse.gdse68.springpossystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author : sachini
 * @date : 2024-10-04
 **/
@Repository
public interface CustomerDAO extends JpaRepository<Customer,String> {
    Customer getCustomerById (String id);
}
