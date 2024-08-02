package com.whizFortuneRestaurant.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    public OrderStatus getOrderStatusById(long id) {
        return orderStatusRepository.findById(id).orElse(null);
    }
    public void addOrderStatus(OrderStatus orderStatus){
        orderStatusRepository.save(orderStatus);
    }
    public OrderStatus updateOrderStatus(long id, OrderStatus orderStatus){
        OrderStatus orderStatus1 = getOrderStatusById(id);
        orderStatus1.setOrder(orderStatus.getOrder());
        orderStatus1.setOrderstatus(orderStatus.getOrderstatus());
        orderStatus1.setOrdertype(orderStatus.getOrdertype());
        return orderStatusRepository.save(orderStatus1);
    }
    public void deleteOrderStatusById(long id){
        orderStatusRepository.deleteById(id);
    }
}
