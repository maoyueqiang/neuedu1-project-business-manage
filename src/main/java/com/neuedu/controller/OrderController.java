package com.neuedu.controller;

import com.neuedu.pojo.Order;
import com.neuedu.pojo.OrderItem;
import com.neuedu.pojo.Product;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/order/")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * 显示订单列表
     * @param session
     * @return
     */
    @RequestMapping(value = "find/{currentPage}/{size}",method = RequestMethod.GET)
    public String findall(@PathVariable(value = "currentPage") Integer currentPage,
                          @PathVariable("size") Integer size,
                          HttpSession session){

        //求总页数
        List<Order> products=orderService.findAll();
//        System.out.println(products);
        int count=products.size();
        int pages=(count%size==0)?count/size:(count/size+1);

        //查找指定页的商品
        int index=(currentPage-1)*size;
        List<Order> ordertList=orderService.findProductInOnePage(index,size);

        session.setAttribute("ordertList",ordertList);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("size",size);
        session.setAttribute("pages",pages);

        return "order/list";
    }

    /**
     * 订单详情
     */
    @RequestMapping(value = "details/{orderNo}",method = RequestMethod.GET)
    public String details(@PathVariable(value = "orderNo") Long orderNo,HttpSession session){

        Order order =orderService.findOrderByOrderno(orderNo);

        List<OrderItem> orderItemList = orderService.findOrderitemByOrderno(orderNo);

        session.setAttribute("order",order);
        session.setAttribute("orderItemList",orderItemList);

        return "order/detail";

    }

    /**
     * 发货
     */
    @RequestMapping(value = "postProduct/{orderNo}",method = RequestMethod.GET)
    public String postProduct(@PathVariable(value = "orderNo") Long orderNo,HttpSession session){

        Order order =orderService.findOrderByOrderno(orderNo);
        order.setStatus(40);

        orderService.updateSendtimeByOrderno(order);

        List<OrderItem> orderItemList = orderService.findOrderitemByOrderno(orderNo);

        session.setAttribute("order",order);
        session.setAttribute("orderItemList",orderItemList);

        return "order/detail";


    }

}
