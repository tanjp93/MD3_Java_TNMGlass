package projectBase_00.service.productService;

import projectBase_00.model.product.Product;
import projectBase_00.service.IGenericService;

import java.util.List;

public interface IProductService extends IGenericService <Product>{
    List<Product> sortProductByCategoryId(int categoryId);
}
