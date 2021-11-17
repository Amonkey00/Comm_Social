package com.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Order {

    private int orderId;
    private int goodId;
    private int customerId;
    private Date orderDate;

    public Order(int orderId, int goodId, int customerId, Date orderDate) {
        this.orderId = orderId;
        this.goodId = goodId;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }
}
