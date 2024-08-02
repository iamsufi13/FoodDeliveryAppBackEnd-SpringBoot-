package com.whizFortuneRestaurant.Orders;

import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
//        List<Orders>  orders =orderRepository.findAll();
//        return orders.stream().map(OrderDto::to)
        return null;
    }

    public Orders getByIdOrder(long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Orders updateOrders(long id,Orders orders){
        Orders orders1 = getByIdOrder(id);
        orders1.setOrderStatus(orders.getOrderStatus());
        orders1.setMrp(orders.getMrp());
        orders1.setPrice(orders.getPrice());
        orders1.setAvailableSizes(orders.getAvailableSizes());
        orders.setDiscountprice(orders.getDiscountprice());
        orders.setProducts(orders.getProducts());
        orders.setQty(orders.getQty());
        orders.setTxnid(orders.getTxnid());
        return orderRepository.save(orders1);
    }
    public void addOrders(Orders orders){
        orderRepository.save(orders);
    }
    public void deleteByIdOrders(long id){
        orderRepository.deleteById(id);
    }
}
