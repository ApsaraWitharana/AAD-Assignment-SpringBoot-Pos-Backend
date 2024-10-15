package lk.ijse.gdse68.springpossystem.dao;

import lk.ijse.gdse68.springpossystem.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@Repository
public interface OrdersDAO extends JpaRepository<Orders,String> {
}
