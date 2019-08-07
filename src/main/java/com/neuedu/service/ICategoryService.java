package com.neuedu.service;

import com.neuedu.exception.MyException;
import com.neuedu.pojo.Category;

import java.util.List;

public interface ICategoryService {



    /**
     * 添加类别
     */
    public int addCategory(Category category) throws MyException;

    public int deleteCategory(int categoryId) throws MyException;

    public int updateCategory(Category category) throws MyException;

    public List<Category> findAll() throws MyException;


    /**
     * 根据类别id查询类别信息
     */
    public Category findCategoryById(int categoryId);
}
