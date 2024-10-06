package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dao.ItemDAO;
import lk.ijse.gdse68.springpossystem.dto.ItemDTO;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.util.AppUtil;
import lk.ijse.gdse68.springpossystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setCode(AppUtil.createCustomerId());
        var Item = mapping.convertToEntity(itemDTO);
        var saveItem = itemDAO.save(Item);
        System.out.println(itemDTO);

        if (saveItem == null){
            throw new DataPersistFailedException("Customer save Note found!");
        }

    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public CustomerResponse getSelectedItem(String id) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return null;
    }
}
