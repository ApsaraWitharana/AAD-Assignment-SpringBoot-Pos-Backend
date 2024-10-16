package lk.ijse.gdse68.springpossystem.service;

import jakarta.transaction.Transactional;

import lk.ijse.gdse68.springpossystem.dao.CustomerDAO;
import lk.ijse.gdse68.springpossystem.dao.ItemDAO;
import lk.ijse.gdse68.springpossystem.dao.OrderDetailsDAO;
import lk.ijse.gdse68.springpossystem.dao.OrdersDAO;
import lk.ijse.gdse68.springpossystem.dto.OrderDetailsDTO;
import lk.ijse.gdse68.springpossystem.dto.OrdersDTO;
import lk.ijse.gdse68.springpossystem.entity.Customer;
import lk.ijse.gdse68.springpossystem.entity.Item;
import lk.ijse.gdse68.springpossystem.entity.OrderDetails;
import lk.ijse.gdse68.springpossystem.entity.Orders;
import lk.ijse.gdse68.springpossystem.exception.DataPersistFailedException;
import lk.ijse.gdse68.springpossystem.exception.ItemNoteFound;
import lk.ijse.gdse68.springpossystem.util.AppUtil;
import lk.ijse.gdse68.springpossystem.util.Mapping;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : sachini
 * @date : 2024-10-14
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private final OrdersDAO ordersDAO;
    @Autowired
    private final ItemDAO itemDAO;
    @Autowired
    private final CustomerDAO customerDAO;
    @Autowired
    private final Mapping mapping;
     @Autowired
     private OrderDetailsDAO orderDetailsDAO;
    @Override
    public String placeOrder(OrdersDTO ordersDTO) {

        // Find customer by ID
        Customer customer = customerDAO.findById(ordersDTO.getCustomer_id())
                .orElseThrow(() -> new RuntimeException("Customer not found!!"));

        // Create the Orders entity and set its fields
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setOrder_id(AppUtil.createOrderId());
        order.setDate(AppUtil.getCurrentDate());
        order.setTotal(ordersDTO.getTotal());
        order.setTxtCash(ordersDTO.getTxtCash());
        order.setTatDiscount(ordersDTO.getTxtDiscount());

        // Save the Orders entity after all order details are processed
       Orders saveOrder =  ordersDAO.save(order);

        // Set order details
//        List<OrderDetails> orderDetailsList = ordersDTO.getOrderDetails().stream().map(detailDTO -> {
//            OrderDetails orderDetails = mapping.convertToOrderDetailsEntity(detailDTO);
//            orderDetails.setOrders(order); // Associate order with orderDetails
//            return orderDetails;
//        }).collect(Collectors.toList());
//
//        // Set the order details in the Orders entity
//        order.setOrderDetails(orderDetailsList);

        // Update item quantities and save order details
        for (OrderDetailsDTO detailDTO : ordersDTO.getOrderDetails()) {
            updateItemQty(detailDTO); // Update item quantity

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setId(detailDTO.getOrder_id());
            orderDetails.setQty(detailDTO.getQty());
            orderDetails.setPrice(detailDTO.getPrice());
//            orderDetails.setOrders(order);
//            orderDetails.setItem(itemDAO.findById(detailDTO.getItem_code())
//                    .orElseThrow(() -> new RuntimeException("Item not found!!")));
//            orderDetails.setQty(detailDTO.getQty());
//            orderDetails.setPrice(detailDTO.getPrice());

            // Retrieve the ItemEntity from the database
            Item item = itemDAO.findById(detailDTO.getItem_code())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid item code:"+detailDTO.getItem_code()));
            orderDetails.setItem(item);
            orderDetails.setOrders(saveOrder);
            orderDetailsDAO.save(orderDetails);  // Save the order detail
        }

        if (saveOrder == null){
            throw new DataPersistFailedException("order note save!");
        }
        return order.getOrder_id();  // Return order ID after successful save
    }


    private boolean updateItemQty(OrderDetailsDTO orderDetailsDTO) {
        String itemId = orderDetailsDTO.getItem_code();
        Optional<Item> optionalItem = itemDAO.findById(itemId);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            // Update the quantity of the item
            item.setQty(item.getQty() - orderDetailsDTO.getQty());
            itemDAO.save(item);
            return true;
        } else {
            // Throw custom exception if the item is not found
            throw new ItemNoteFound("Item with id " + itemId + " not found!");
        }
    }
}
