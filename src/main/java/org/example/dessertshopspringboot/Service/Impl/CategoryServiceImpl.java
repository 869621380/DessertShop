package org.example.dessertshopspringboot.Service.Impl;

import org.example.dessertshopspringboot.Domain.Category;
import org.example.dessertshopspringboot.Persistence.CategoryMapper;
import org.example.dessertshopspringboot.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
//    @Override
//    public List<Category> getCategoryList() {
//        return categoryMapper.getCategoryList();
//    }
//
    @Override
    public Category getCategoryById(int id) {
        return categoryMapper.getCategoryById(id);
    }
    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategory(int id) {
        return categoryMapper.getCategory(id);
    }
    @Override
    public List<Category> searchProductList(String keywords) {
        return categoryMapper.searchProductList(keywords);
    }

    @Override
    public void sellGoods(int id, int quantity){
        categoryMapper.sellGoods(id,quantity);
    }

    @Override
    public int getRemain(int id){
        return categoryMapper.getRemain(id);
    }
}
