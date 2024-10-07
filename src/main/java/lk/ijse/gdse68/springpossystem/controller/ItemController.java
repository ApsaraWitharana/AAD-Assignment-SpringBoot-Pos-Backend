package lk.ijse.gdse68.springpossystem.controller;

import lk.ijse.gdse68.springpossystem.dto.ItemDTO;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.exception.ItemNoteFound;
import lk.ijse.gdse68.springpossystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    //TODO: Crud
    private final ItemService itemService;


    //TODO: ItemSave
    @PostMapping
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDTO){
          // Validate code (pattern: ITM-000)
        if (itemDTO.getCode() == null || !itemDTO.getCode().matches("^ITM-[0-9]{3}$")) {
            return new ResponseEntity<>("Item code is empty or invalid! It should match 'ITM-000' format.", HttpStatus.BAD_REQUEST);
        }

        // Validate name (at least 4 alphabetic characters)
        if (itemDTO.getName() == null || !itemDTO.getName().matches("^[A-Za-z ]{4,}$")) {
            return new ResponseEntity<>("Item name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
        }

        // Validate price (should be greater than 0)
        if (itemDTO.getPrice() == null || itemDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>("Item price is empty or invalid! It must be a positive decimal.", HttpStatus.BAD_REQUEST);
        }

        // Validate quantity (should be greater than 0)
        if (itemDTO.getQty() <= 0) {
            return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
        }
            try {
                itemService.saveItem(itemDTO);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PatchMapping(value = "/{code}")
        public ResponseEntity<String> updateItem(@PathVariable("code") String code,@RequestBody ItemDTO itemDTO){

            if (itemDTO.getName() == null || !itemDTO.getName().matches("^[A-Za-z ]{4,}$")) {
                return new ResponseEntity<>("Item name is empty or invalid! It should contain at least 4 alphabetic characters.", HttpStatus.BAD_REQUEST);
            }
            if (itemDTO.getPrice() == null || itemDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                return new ResponseEntity<>("Item price is empty or invalid! It must be a positive decimal.", HttpStatus.BAD_REQUEST);
            }
            if (itemDTO.getQty() <= 0) {
                return new ResponseEntity<>("Item quantity is empty or invalid! It must be greater than 0.", HttpStatus.BAD_REQUEST);
            }

            try {
                itemService.updateItem(code,itemDTO);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (ItemNoteFound e){
                return new ResponseEntity<>("Item not found!",HttpStatus.NOT_FOUND); //return 404 item is not found
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

