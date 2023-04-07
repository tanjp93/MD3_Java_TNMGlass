package projectBase_00.view;


import projectBase_00.config.Config;
import projectBase_00.controller.CategoryController;
import projectBase_00.controller.ProductController;
import projectBase_00.model.category.Category;
import projectBase_00.model.product.Product;

import java.util.List;

public class ProductView {
    ProductController productController = new ProductController();
    List<Product> productList = productController.getListProduct();

    CategoryController categoryController = new CategoryController();


    public ProductView() {
    }

    public void menuProduct() {
        while (true) {
            System.out.println("======================= Product ===========================");
            System.out.println("1. All Product");
            System.out.println("2. Create a new product");
            System.out.println("3. Update product");
            System.out.println("4. Delete Product");
            System.out.println("Enter 0 to back to Menu");
            int choice = Integer.parseInt(Config.scanner.nextLine());
            switch (choice) {
                case 0:
                    new Navbar();
                    break;
                case 1:
                    showAllListProduct();
                    break;
                case 2:
                    createProduct();
                    break;
                case 3:
//                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                default:
                    System.out.println("Input !");
                    break;
            }
        }
    }

    private void createProduct() {
        int id = 0;
        if (productList.isEmpty()) {
            id = 1;
        } else id = productList.get(productList.size() - 1).getId() + 1;
        System.out.println("Input Product Information");
        System.out.println("Input Product name");
        String productName = Config.scanner.nextLine();
        System.out.println("Describe Product");
        String describe = Config.scanner.nextLine();
        System.out.println("Product Image URL :");
        String img = Config.scanner.nextLine();
        System.out.println("Product's Price");
        long price = Long.parseLong(Config.scanner.nextLine());
        System.out.println("Category type : ");
        new CategoryView().showFormCategoryList();
        System.out.println("Enter Category type: ");
        int categoryId = Integer.parseInt(Config.scanner.nextLine());
        Category category = categoryController.detailCategory(categoryId);
        Product newProduct = new Product(id, productName, describe, img, price, category);
        productController.createNewProduct(newProduct);
        System.out.println("Create Product success !");
        System.out.println("---------------------------------------------------------------------------");
        menuProduct();
    }


    public void deleteProduct() {
        showAllListProduct();
        System.out.println("Input id Product!");
        int id=Integer.parseInt(Config.scanner.nextLine());
        productController.deleteProduct(id);
    }


    public void showListProductByCategory(int categoryId) {

    }

    public void showAllListProduct() {
        System.out.println("===================== Products ========================");
        System.out.println("----ID----Product----");
        if (productList.size() == 0) {
            System.out.println("No product found ! ");
        } else {
            for (int i = 0; i < productList.size(); i++) {
                System.out.println("----" + productList.get(i).getId() + "----" +
                        productList.get(i).getProductName());
            }
        }
    }

}