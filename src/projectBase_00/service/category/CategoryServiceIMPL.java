package projectBase_00.service.category;

import projectBase_00.config.Config;
import projectBase_00.model.category.Category;
import projectBase_00.model.product.Product;
import projectBase_00.service.productService.ProductServiceIPML;

import java.util.List;

public class CategoryServiceIMPL implements ICategoryService {
    List<Category> categories = new Config<Category>().readFromFile(Config.PATH_CATEGORY);
//    List<Product> products = new Config<Product>().readFromFile(Config.PATH_LIST_PRODUCT);

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public Category findById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    @Override
    public void save(Category category) {
        if (findById(category.getId())==null){
            //Create a new category
            categories.add(category);
        }
        else categories.set(categories.indexOf(findById(category.getId())),category);
        new Config<Category>().writeToFile(Config.PATH_CATEGORY, categories);
//        new Config<Product>().writeToFile(Config.PATH_LIST_PRODUCT,products);
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.remove(i);
                break;
            }
        }
    }
}
