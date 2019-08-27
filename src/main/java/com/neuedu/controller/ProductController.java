package com.neuedu.controller;

import com.github.pagehelper.PageHelper;
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

    /**
     * 显示商品列表
     * @param session
     * @return
     */
    @RequestMapping(value = "find/{currentPage}/{size}",method = RequestMethod.GET)
    public String findall(@PathVariable(value = "currentPage") Integer currentPage,
                          @PathVariable("size") Integer size,
                          HttpSession session){

        //求总页数
        List<Product> products=productService.findAll();
//        System.out.println(products);
        int count=products.size();
        int pages=(count%size==0)?count/size:(count/size+1);

        //查找指定页的商品
        int index=(currentPage-1)*size;
        List<Product> productList=productService.findProductInOnePage(index,size);


        session.setAttribute("productList",productList);
        session.setAttribute("currentPage",currentPage);
        session.setAttribute("size",size);
        session.setAttribute("pages",pages);


        return "product/list";
    }

    /**
     * 更新商品
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, HttpServletRequest request){
        Product product = productService.findById(id);

        List<Category> categoryList = categoryService.findAll();

        request.setAttribute("product",product);
        request.setAttribute("categoryList",categoryList);
        return "product/index";

    }

    /**
     * 新增商品
     * @param request
     * @return
     */
    @RequestMapping(value = "insert",method = RequestMethod.GET)
    public String insert(HttpServletRequest request){
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList",categoryList);
        return "product/index";
    }

    /**
     * 新增或更新商品的表单提交
     * @param product
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "insertorupdate",method = RequestMethod.POST)
    public String insertorupdate(Product product,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(product.getId()!=null){
            int count=productService.updateById(product);
            if(count>0){
                int currentPage = (int)session.getAttribute("currentPage");
                int size = (int)session.getAttribute("size");
                return "redirect:/user/product/find/"+currentPage+"/"+size;
            }

            return "product/index";
        }
        int count = productService.insert(product);
        if(count>0){
            return "redirect:/user/product/find/1/10";
        }
        throw new MyException("添加失败");

    }

    /**
     * 删除商品的表单提交
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id,HttpSession session){
        int count = productService.delete(id);
        if(count>0){
            int currentPage = (int)session.getAttribute("currentPage");
            int size = (int)session.getAttribute("size");
            return "redirect:/user/product/find/"+currentPage+"/"+size;
        }
        throw new MyException("删除失败");

    }

    /**
     * 查看商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "details/{id}",method = RequestMethod.GET)
    public String details(@PathVariable("id") Integer id, HttpServletRequest request){
        Product product = productService.findById(id);
        if(product!=null&&!product.equals("")){
            request.setAttribute("product",product);
            return "product/details";
        }
        throw new MyException("查询失败");
    }


}
