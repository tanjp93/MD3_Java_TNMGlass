package projectBase_00.controller;

import projectBase_00.model.category.Category;
import projectBase_00.service.category.CategoryServiceIMPL;
import projectBase_00.service.category.ICategoryService;

import java.util.List;

public class CategoryController {
    public ICategoryService iCategoryService = new CategoryServiceIMPL();

    public List<Category> getListCategory() {
        return iCategoryService.findAll();
    }

    public void createCategoryToDB(Category category) {
        iCategoryService.save(category);
    }

    public void updateCategory(Category category) {
        iCategoryService.save(category);
    }

    public Category detailCategory(int id) {
        return iCategoryService.findById(id);
    }

    public void deleteCategory(int id) {
        iCategoryService.deleteById(id);
    }
}
