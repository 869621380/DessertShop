package org.example.dessertshopspringboot.Service;

import org.example.dessertshopspringboot.Domain.Category;

import java.util.List;

public interface CategoryService {
//
//    List<Category> getCategoryList();
//
    Category getCategoryById(int id);

    List<Category> searchProductList(String keyword);
    List<Category> getCategoryList();

    Category getCategory(int id);

    void sellGoods(int id,int quantity);

    int getRemain(int id);
}
