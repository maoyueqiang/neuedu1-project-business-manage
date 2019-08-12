package com.neuedu.controller;

import com.neuedu.exception.MyException;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user/product/")
public class ProductController {

    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = "find",method = RequestMethod.GET)
    public String findall(HttpSession session){
        List<Product> productList=productService.findAll();
        System.out.println(productList);

        session.setAttribute("productList",productList);
        return "product/list";
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, HttpServletRequest request){
        Product product = productService.findById(id);

        List<Category> categoryList = categoryService.findAll();

        request.setAttribute("product",product);
        request.setAttribute("categoryList",categoryList);
        return "product/index";

    }
    @RequestMapping(value = "insert",method = RequestMethod.GET)
    public String insert(HttpServletRequest request){
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList",categoryList);
        return "product/index";
    }

    @RequestMapping(value = "insertorupdate",method = RequestMethod.POST)
    public String insertorupdate(Product product,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(product.getId()!=null){
            int count=productService.updateById(product);
            if(count>0)
                return "redirect:/user/product/find";
            return "product/index";
        }
        int count = productService.insert(product);
        if(count>0){
            return "redirect:/user/product/find";
        }
        throw new MyException("添加失败");

    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id){
        int count = productService.delete(id);
        if(count>0){
            return "redirect:/user/product/find";
        }
        throw new MyException("删除失败");
    }



}
