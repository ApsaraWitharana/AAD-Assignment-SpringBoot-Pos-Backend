package lk.ijse.gdse68.springpossystem.controller;


import lk.ijse.gdse68.springpossystem.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystem.entity.Customer;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : sachini
 * @date : 2024-10-12
 **/
@RestController
@RequestMapping("api/v1/place_order")
@RequiredArgsConstructor
public class PlaceOrderController {
    @Autowired
    PlaceOrderService placeOrderService;
    Logger logger = LoggerFactory.getLogger(Customer.class);
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<String> placeOrder(@RequestBody OrdersDTO ordersDTO){
//        try {
//            placeOrderService.placeOrder(ordersDTO);
//            logger.info("Order Save Successfully!!");
//            return new  ResponseEntity<>(HttpStatus.CREATED);
//        }catch (DataPersistFailedException e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        if (ordersDTO == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                String orderId = placeOrderService.placeOrder(ordersDTO);
                logger.info("Order saved successfully with ID: " + orderId);
                return ResponseEntity.status(HttpStatus.CREATED).body("Order created with ID: " + orderId);
            } catch (DataPersistFailedException e) {
                logger.error("Data persist failed: ", e);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data persist failed!");
            } catch (Exception e) {
                logger.error("Internal server error: ", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error!");
            }
        }
    }
}
