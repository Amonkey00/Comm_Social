package com.demo.dao;

import com.demo.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    // Get Order list
    List<Order> getOrderList();

    // Get Order By Id
    Order getOrderById(@Param("orderId") int orderId);

    // Add Order
    int addOrder(Order order);

    // Delete Order
    int deleteOrder(@Param("orderId") int orderId);
}
