package projectBase_00.service.productService;

import projectBase_00.config.Config;
import projectBase_00.model.category.Category;
import projectBase_00.model.product.Product;
import projectBase_00.service.category.CategoryServiceIMPL;
import projectBase_00.service.category.ICategoryService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceIPML implements IProductService {
    List<Product> listProduct = new Config<Product>().readFromFile(Config.PATH_LIST_PRODUCT);
    ICategoryService categoryServiceIMPL = new CategoryServiceIMPL();

    @Override
    public List<Product> findAll() {
        return listProduct;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getId()) == null) {
            //create new product
            listProduct.add(product);
        } else {
            //update new product
            listProduct.set(listProduct.indexOf(findById(product.getId())), product);
        }
        new Config<Product>().writeToFile(Config.PATH_LIST_PRODUCT, listProduct);
    }

    @Override
    public Product findById(int id) {
        for (Product product : listProduct) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (findById(id) == null) {
            System.out.println("Product Not Found !");
            return;
        } else {
            listProduct.remove(findById(id));
        }
        new Config<Product>().writeToFile(Config.PATH_LIST_PRODUCT, listProduct);
    }

    @Override
    public List<Product> sortProductByCategoryId(int categoryId) {
        List<Product> listProductSort = new ArrayList<>();
        Category category = categoryServiceIMPL.findById(categoryId);
        for (Product product : listProduct) {
            if (product.getCategory().equals(category)) {
                listProductSort.add(product);
            }
        }
        return listProductSort;
    }

    @Override
    public void updateAllProductByCategory(Category cate) {
        for (Product product:listProduct) {
            if (product.getCategory().getId()==cate.getId()){
                product.setCategory(cate);
            }
        }
        new Config<Product>().writeToFile(Config.PATH_LIST_PRODUCT, listProduct);
    }
}
