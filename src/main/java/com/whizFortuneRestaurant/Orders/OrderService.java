package com.whizFortuneRestaurant.Orders;

import com.whizFortuneRestaurant.AvailableISizes.AvailableSizes;
import com.whizFortuneRestaurant.AvailableISizes.AvailableSizesRepository;
import com.whizFortuneRestaurant.OrderProduct.OrderProduct;
import com.whizFortuneRestaurant.OrderProduct.OrderProductRepository;
import com.whizFortuneRestaurant.OrderStatus.OrderStatus;
import com.whizFortuneRestaurant.OrderStatus.OrderStatusRepository;
import com.whizFortuneRestaurant.Product.Product;
import com.whizFortuneRestaurant.Product.ProductRepository;
import com.whizFortuneRestaurant.Users.User;
import com.whizFortuneRestaurant.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AvailableSizesRepository availableSizesRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<OrderDto> getAllOrders() {
        List<Orders> list = orderRepository.findAll();
        return list.stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
    }

    public Orders getByIdOrder(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Orders updateOrders(long id, OrderDto orderDto) {
        Orders existingOrder = getByIdOrder(id);
        if (existingOrder != null) {
            existingOrder.setTxnid(orderDto.getTxnid());
            existingOrder.setPrice(orderDto.getPrice());
            existingOrder.setMrp(orderDto.getMrp());
            existingOrder.setQty(orderDto.getQty());
            existingOrder.setStatus(orderDto.getStatus());
            existingOrder.setDiscountprice(orderDto.getDiscountPrice());

            // Handle relationships
            List<Product> products = getProductsByIds(orderDto.getProductIds());
            existingOrder.setProducts(products);

            List<AvailableSizes> availableSizes = getAvailableSizesByIds(orderDto.getAvailableSizeIds());
            existingOrder.setAvailableSizes(availableSizes);

            OrderStatus orderStatus = getOrderStatusById(orderDto.getOrderStatusId());
            existingOrder.setOrderStatus(orderStatus);

            User user = getUserById(orderDto.getUserId());
            existingOrder.setUser(user);

            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public Orders addOrders(OrderDto orderDto) {
        Orders newOrder = new Orders();
        newOrder.setTxnid(orderDto.getTxnid());
        newOrder.setPrice(orderDto.getPrice());
        newOrder.setMrp(orderDto.getMrp());
        newOrder.setQty(orderDto.getQty());
        newOrder.setStatus(orderDto.getStatus());
        newOrder.setDiscountprice(orderDto.getDiscountPrice());

        OrderStatus orderStatus = getOrderStatusById(orderDto.getOrderStatusId());
        newOrder.setOrderStatus(orderStatus);

        User user = getUserById(orderDto.getUserId());
        newOrder.setUser(user);

        Orders savedOrder = orderRepository.save(newOrder);

        // Handle order-product associations
        List<Product> products = getProductsByIds(orderDto.getProductIds());
        for (Product product : products) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(savedOrder);
            orderProduct.setProduct(product);
            orderProductRepository.save(orderProduct);
        }

        return savedOrder;
    }


    public void deleteByIdOrders(long id) {
        orderRepository.deleteById(id);
    }

    // Retrieve entities by IDs

    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }

    public List<AvailableSizes> getAvailableSizesByIds(List<Long> availableSizeIds) {
        return availableSizesRepository.findAllById(availableSizeIds);
    }

    public OrderStatus getOrderStatusById(long orderStatusId) {
        return orderStatusRepository.findById(orderStatusId).orElse(null);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
