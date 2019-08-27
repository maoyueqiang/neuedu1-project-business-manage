package com.neuedu.service;

import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;

import java.util.List;

public interface IOrderService {

    List<Order> findAll();

    List<Order> findProductInOnePage(Integer index,Integer size);

    List<OrderItem> findOrderitemByOrderno(Long orderNo);

    Order findOrderByOrderno(Long orderNo);

    int updateSendtimeByOrderno(Order order);





}
