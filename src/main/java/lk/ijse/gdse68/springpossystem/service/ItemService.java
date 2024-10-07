package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String code, ItemDTO itemDTO);
    void deleteItem(String code);
    CustomerResponse getSelectedItem(String code);

    List<ItemDTO> getAllItem();
}
