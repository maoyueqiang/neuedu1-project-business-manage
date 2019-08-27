package com.neuedu.service;

import com.neuedu.exception.MyException;
import com.neuedu.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {

    public List<Product> findAll() throws MyException;

    public Product findById(Integer id) throws MyException;

    public int updateById(Product product) throws MyException;

    public int insert(Product product) throws MyException;

    public int delete(int id) throws MyException;

    List<Product> findProductInOnePage(Integer index,Integer size);


}
