package com.neuedu.service.Impl;

import com.neuedu.dao.OrderItemMapper;
import com.neuedu.dao.OrderMapper;
import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }

    @Override
    public List<Order> findProductInOnePage(Integer index, Integer size) {
        return orderMapper.findProductInOnePage(index,size);
    }

    @Override
    public List<OrderItem> findOrderitemByOrderno(Long orderNo) {
        return orderItemMapper.findOrderitemByOrderno(orderNo);
    }

    @Override
    public Order findOrderByOrderno(Long orderNo) {
        return orderMapper.findOrderByOrderno(orderNo);
    }

    @Override
    public int updateSendtimeByOrderno(Order order) {
        return orderMapper.updateSendtimeByOrderno(order);
    }
}
