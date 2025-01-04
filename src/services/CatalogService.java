package services;

import persistence.CategoryDao;
import persistence.impl.CategoryDaoImpl;
import domain.Category;

import java.util.List;

public class CatalogService {
    private CategoryDao categoryDao;
    public CatalogService()
    {
        this.categoryDao = new CategoryDaoImpl();
    }
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String name) {
        return categoryDao.getCategory(name);
    }



    // TODO enable using more than one keyword


}
