package projectBase_00.view;


import projectBase_00.config.Config;
import projectBase_00.config.InputMethod;
import projectBase_00.controller.CartController;
import projectBase_00.controller.CategoryController;
import projectBase_00.controller.ProductController;
import projectBase_00.controller.UserController;
import projectBase_00.dto.response.ResponseMessage;
import projectBase_00.model.cart.Cart;
import projectBase_00.model.cart.OrderProduct;
import projectBase_00.model.category.Category;
import projectBase_00.model.product.Product;
import projectBase_00.model.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductView {
    ProductController productController = new ProductController();
    List<Product> productList = productController.getListProduct();
    CategoryController categoryController = new CategoryController();
    UserController userController = new UserController();
    List<ResponseMessage> listResponse = userController.checkRole();
    List<Cart> listCart = new CartController().getListCart();
    //check Login
    User userLogin = userController.getUserLogin();

    public ProductView() {
    }

    public void menuProduct() {
        while (true) {
            System.out.println("======================= Product ===========================");
            System.out.println("1. Show List Product");
            if (userController.getUserLogin() != null) {
                if (listResponse.get(0).getMessage() != "user") {
                    System.out.println("2. Create a new product");
                    System.out.println("3. Update product");
                    System.out.println("4. Delete Product");
                }
            }
            System.out.println("Enter 0 to back to Menu");
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 0:
                    new Navbar();
                    break;
                case 1:
                    showAllListProduct();
                    if (userController.getUserLogin() != null) {
                        if (listResponse.get(0).getMessage() != "user") {
                            new ProductView().menuProduct();
                        }else new ProductView().buyOrBack();
                    }
                    break;
                case 2:
                    if (listResponse.get(0).getMessage() != "user") {
                        createProduct();
                    } else {
                        System.out.println("Invalid number");
                    }
                    menuProduct();
                    break;
                case 3:
                    if (listResponse.get(0).getMessage() != "user") {
                        updateProduct();
                    } else {
                        System.out.println("Invalid number");
                    }
                    menuProduct();
                    break;
                case 4:
                    if (listResponse.get(0).getMessage() != "user") {
                        deleteProduct();
                    } else {
                        System.out.println("Invalid number");
                    }
                    menuProduct();
                    break;
                default:
                    System.out.println("Input invalid!");
                    menuProduct();
                    break;
            }
        }
    }

    private void createProduct() {
        int id = 0;
        if (productList.isEmpty()) {
            id = 1;
        } else id = productList.get(productList.size() - 1).getId() + 1;
        Product newProduct = new Product();
        new CategoryView(newProduct).showFormCategoryList();
        System.out.println("Category type : ");
        int categoryId = InputMethod.getInteger();
        System.out.println("Input Product Information");
        System.out.println("Input Product name");
        String productName =InputMethod.getString();
        System.out.println("Describe Product");
        String describe = InputMethod.getString();
        System.out.println("Product Image URL :");
        String img = InputMethod.getString();
        System.out.println("Product's Price");
        long price = InputMethod.getLong();
        System.out.println("Stoke of Product");
        int  stoke = InputMethod.getInteger();
        // Input End
        newProduct.setId(id);
        newProduct.setProductName(productName);
        newProduct.setDescribe(describe);
        newProduct.setPrice(price);
        newProduct.setImg(img);
        newProduct.setCategory(categoryController.detailCategory(categoryId));
        newProduct.setStoke(stoke);
        productController.createNewProduct(newProduct);
        System.out.println("Create Product success !");
        System.out.println("---------------------------------------------------------------------------");
        menuProduct();
    }

    private void updateProduct() {
        System.out.println("Input Product Information");
        Product product = findProductById();
        System.out.println("Update new name");
        String productName = InputMethod.getString();
        System.out.println("Update new describe");
        String describe = InputMethod.getString();
        System.out.println("Update new Price");
        long price = Long.parseLong(InputMethod.getString());
        System.out.println("Update new image");
        String image = InputMethod.getString();
        System.out.println("Select Category of Product ");
        new CategoryView(product).showFormCategoryList();
        int categoryId = Integer.parseInt(InputMethod.getString());

        System.out.println("Set Items in stoke");
        int stoke=InputMethod.getInteger();
        product.setProductName(productName);
        product.setDescribe(describe);
        product.setPrice(price);
        product.setImg(image);
        product.setStoke(stoke);
        product.setCategory(categoryController.detailCategory(categoryId));
        productController.updateProduct(product);
        System.out.println("Update Product success !");
        System.out.println("---------------------------------------------------------------------------");
        menuProduct();
    }


    public void deleteProduct() {
        showAllListProduct();
        System.out.println("Input id Product!");
        int id = Integer.parseInt(InputMethod.getString());
        productController.deleteProduct(id);
    }

    public void buyOrBack() {
        System.out.println("0.Back to Menu");
        System.out.println("1.Buy Now");
        System.out.println("2.View Detail");
//        int choice = Integer.parseInt(InputMethod.getString());
        int choice = InputMethod.getInteger();
        switch (choice) {
            case 0:
                new Navbar();
                break;
            case 1:
                buyProduct();
                new Navbar();
                break;
            case 2:
                findProductById();
                buyOrBack();
                break;
            default:
                System.out.println("Invalid Input");
                buyOrBack();
                break;
        }
    }
    public void buyProduct() {
        if (userLogin == null) {
            System.out.println("Login first !");
            new UserView().formLogin();
            buyProduct();
        }
        OrderProduct orderProduct = new OrderProductView().oderListProduct();
        if (orderProduct==null){
            System.out.println("Your Order Failed !");
            orderProduct = new OrderProductView().oderListProduct();
        }
        new CartView().addProductToListCart(userLogin, orderProduct);
        new CartView().showUserCart(userLogin);
        new CartView().menuBuyMoreOrDeleteProduct(userLogin);

        //cap nhat lai stoke cua product
    }


    public void showListProductByCategory(int categoryId) {
        Category category = categoryController.detailCategory(categoryId);
        System.out.println("===================== Products ========================");
        for (Product product : productList) {
            if (product.getCategory().getName().equals(category.getName())) {
                System.out.println("----ID----Product----Describe----Image----Price----Category----Stoke");
                System.out.println("----" + product.getId() + "----" + product.getProductName() + "----" + product.getDescribe() +
                        "----" + product.getImg() + "----" + product.getPrice() + " vnd" + "----" + product.getCategory().getName()+"    "+product.getStoke()+"--items--");
            }
        }
    }

    public void showAllListProduct() {
        System.out.println("===================== Products ========================");
        System.out.println("----ID----Product----Describe----Image----Price----Category----Stoke");
        if (productList.size() == 0) {
            System.out.println("No product found ! ");
        } else {
            for (int i = 0; i < productList.size(); i++) {
                System.out.println("----" + productList.get(i).getId() + "----" + productList.get(i).getProductName() + "----" + productList.get(i).getDescribe() +
                        "----" + productList.get(i).getImg() + "----" + productList.get(i).getPrice() + " vnd" + "----" + productList.get(i).getCategory().getName()+"    "+productList.get(i).getStoke()+"--items--");
            }
        }
    }

    public Product findProductById(int id) {
        Product product = productController.productDetail(id);
        if (product != null) {
            return product;
        } else {
            System.out.println("Not found Product");
            return null;
        }
    }

    public Product findProductById() {
        System.out.println("Select IdProduct");
        int id = InputMethod.getInteger();
        Product product = productController.productDetail(id);
        if (product != null) {
            System.out.println("Product detail :");
            System.out.println("----ID----Product----Describe----Image----Price----Category");
            System.out.println("----" + product.getId() + "----" + product.getProductName() + "----" + product.getDescribe() + "----" + product.getImg() +
                    "----" + product.getPrice() + " vnd" + "----" + product.getCategory().getName());
            return product;
        } else
            System.out.println("Not found Product");
        return null;
    }
    public void searchProductByName(){
        System.out.println("Input your product's search : ");
        String productName=InputMethod.getString();
        System.out.println("----ID----Product----Describe----Image----Price----Category");
        for (Product product:productList) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())){
                System.out.println("----" + product.getId() + "----" + product.getProductName() + "----" + product.getDescribe() + "----" + product.getImg() +
                        "----" + product.getPrice() + " vnd" + "----" + product.getCategory().getName());
            }
        }
    }

}