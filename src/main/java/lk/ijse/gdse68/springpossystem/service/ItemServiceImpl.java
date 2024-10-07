package lk.ijse.gdse68.springpossystem.service;

import lk.ijse.gdse68.springpossystem.customerObj.CustomerResponse;
import lk.ijse.gdse68.springpossystem.dao.ItemDAO;
import lk.ijse.gdse68.springpossystem.dto.ItemDTO;
import lk.ijse.gdse68.springpossystem.entity.Item;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.exception.ItemNoteFound;
import lk.ijse.gdse68.springpossystem.util.AppUtil;
import lk.ijse.gdse68.springpossystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setCode(AppUtil.createItemCode());
        var Item = mapping.convertToEntity(itemDTO);
        var saveItem = itemDAO.save(Item);
        System.out.println(itemDTO);

        if (saveItem == null){
            throw new DataPersistFailedException("Customer save Note found!");
        }

    }

    @Override
    public void updateItem(String code, ItemDTO itemDTO) {
        Optional<Item> tmpItemEntity = itemDAO.findById(code);
        if (!tmpItemEntity.isPresent()){
            throw new ItemNoteFound("Item not Found!!");
        }else {
          Item item = tmpItemEntity.get();
          item.setName(itemDTO.getName());
          item.setPrice(itemDTO.getPrice());
          item.setQty(itemDTO.getQty());
          itemDAO.save(item);
        }

    }

    @Override
    public void deleteItem(String code) {
      Optional<Item> findId = itemDAO.findById(code);
      if (!findId.isPresent()){
          throw new RuntimeException("Item not found!");
      }else {
          itemDAO.deleteById(code);
      }
    }

    @Override
    public CustomerResponse getSelectedItem(String code) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return null;
    }
}
