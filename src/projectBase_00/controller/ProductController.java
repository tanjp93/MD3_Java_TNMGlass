package projectBase_00.controller;

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
    public void productDetail(int id){
        productService.findById(id);
    }
    public List<Product>  showPrdByCategoryId(int categoryId){
        return  productService.sortProductByCategoryId(categoryId);
    }
    public void deleteProduct(int id){
        productService.deleteById(id);
    }
}
