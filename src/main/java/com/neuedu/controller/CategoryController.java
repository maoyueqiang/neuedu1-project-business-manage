package com.neuedu.controller;

import com.neuedu.exception.MyException;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user/category/")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = "find",method = RequestMethod.GET)
    public String findAll(HttpSession session){
        List<Category> categoryList = categoryService.findAll();
        session.setAttribute("categorylist",categoryList);

        return "category/list";
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer categoryId, HttpServletRequest request){
        Category category = categoryService.findCategoryById(categoryId);
        request.setAttribute("category",category);
        return "category/index";
    }
    @RequestMapping(value = "insert",method = RequestMethod.GET)
    public String insert(){
        return "category/index";
    }

    @RequestMapping(value = "insertorupdate",method = RequestMethod.POST)
    public String insert(Category category,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(category.getId()!=null) {
            int count = categoryService.updateCategory(category);
            if(count>0){
                return "redirect:/user/category/find";
            }
            return "category/index";
        }
        int count = categoryService.addCategory(category);
        if(count>0){
            return "redirect:/user/category/find";
        }
        throw new MyException("添加失败");
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer categoryId){
        int count = categoryService.deleteCategory(categoryId);
        if(count>0){
            return "redirect:/user/category/find";
        }
        throw new MyException("删除失败");
    }



}
