package projectBase_00.controller;

import projectBase_00.model.category.Category;
import projectBase_00.model.product.Product;
import projectBase_00.service.productService.IProductService;
import projectBase_00.service.productService.ProductServiceIPML;

import java.util.List;

public class ProductController {
    public IProductService productService=new ProductServiceIPML();
    public List<Product> getListProduct(){
        return productService.findAll();
    }
    public void createNewProduct(Product product){
        productService.save(product);
    }
    public void updateProduct(Product product){
        productService.save(product);
    }
    public Product productDetail(int id){
        return productService.findById(id);
    }
    public List<Product>  showPrdByCategoryId(int categoryId){
        return  productService.sortProductByCategoryId(categoryId);
    }
    public void deleteProduct(int id){
        productService.deleteById(id);
    }
    public  void  updateProductByCategory(Category cate){
        productService.updateAllProductByCategory(cate);
    }
}
