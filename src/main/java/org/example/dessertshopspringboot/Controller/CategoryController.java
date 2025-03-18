package org.example.dessertshopspringboot.Controller;

import org.example.dessertshopspringboot.Domain.Category;
import org.example.dessertshopspringboot.Domain.Result;
import org.example.dessertshopspringboot.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Autowired
    CategoryService categoryService;
//    @GetMapping
//    public Result<List<Category>> getCategory() {
//        List<Category> categoryList = categoryService.getCategoryList();
//        return Result.success(categoryList);
//    }
//
//    @PostMapping("/detail")
//    public Result<Category> getCategoryDetail(@RequestParam("id")Integer id) {
//        Category category=categoryService.getCategoryById(id);
//        System.out.println(category);
//        return Result.success(category);
//    }

    @RequestMapping("/getCategory")
    public Category getCategory(int id)
    {
        return categoryService.getCategory(id);
    }
    @RequestMapping("/getCategoryList")
    public List<Category> getCategoryList()
    {
        return categoryService.getCategoryList();
    }
    @RequestMapping("/searchProductList")
    public List<Category> searchProductList(String keywords)
    {
        return categoryService.searchProductList(keywords);
    }
}
