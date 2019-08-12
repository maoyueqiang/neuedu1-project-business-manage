package com.neuedu.service.Impl;

import com.neuedu.dao.ProductMapper;
import com.neuedu.exception.MyException;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> findAll() throws MyException {
        return productMapper.selectAll();
    }

    @Override
    public Product findById(Integer id) throws MyException {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Product product) throws MyException {
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public int insert(Product product) throws MyException {
        return productMapper.insert(product);
    }

    @Override
    public int delete(int id) throws MyException {
        return productMapper.deleteByPrimaryKey(id);
    }


}
