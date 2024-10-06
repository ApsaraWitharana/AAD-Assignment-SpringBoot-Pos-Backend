package lk.ijse.gdse68.springpossystem.dao;

import lk.ijse.gdse68.springpossystem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<Item,String> {
}
