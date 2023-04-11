package projectBase_00.view;


import projectBase_00.config.Config;
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
            int choice = Integer.parseInt(Config.scanner.nextLine());
            switch (choice) {
                case 0:
                    new Navbar();
                    break;
                case 1:
                    showAllListProduct();
                    menuProduct();
                    break;
                case 2:
                    if (listResponse.get(0).getMessage() != "user") {
                        createProduct();
                        menuProduct();
                    } else {
                        System.out.println("Invalid number");
                        menuProduct();
                    }
                    break;
                case 3:
                    if (listResponse.get(0).getMessage() != "user") {
                        updateProduct();
                        menuProduct();
                    } else {
                        System.out.println("Invalid number");
                        menuProduct();
                    }
                    menuProduct();
                    break;
                case 4:
                    if (listResponse.get(0).getMessage() != "user") {
                        deleteProduct();
                        menuProduct();
                    } else {
                        System.out.println("Invalid number");
                        menuProduct();
                    }
                    break;
                default:
                    System.out.println("Input invalid!");
                    menuProduct();
                    break;
            }
            menuProduct();
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
        int categoryId = Integer.parseInt(Config.scanner.nextLine());
        System.out.println("Input Product Information");
        System.out.println("Input Product name");
        String productName = Config.scanner.nextLine();
        System.out.println("Describe Product");
        String describe = Config.scanner.nextLine();
        System.out.println("Product Image URL :");
        String img = Config.scanner.nextLine();
        System.out.println("Product's Price");
        long price = Long.parseLong(Config.scanner.nextLine());
        // Input End
        newProduct.setId(id);
        newProduct.setProductName(productName);
        newProduct.setDescribe(describe);
        newProduct.setPrice(price);
        newProduct.setImg(img);
        newProduct.setCategory(categoryController.detailCategory(categoryId));
        productController.createNewProduct(newProduct);
        System.out.println("Create Product success !");
        System.out.println("---------------------------------------------------------------------------");
        menuProduct();
    }

    private void updateProduct() {
        System.out.println("Input Product Information");
        Product product = findProductById();
        System.out.println("Update new name");
        String productName = Config.scanner.nextLine();
        System.out.println("Update new describe");
        String describe = Config.scanner.nextLine();
        System.out.println("Update new Price");
        long price = Long.parseLong(Config.scanner.nextLine());
        System.out.println("Update new image");
        String image = Config.scanner.nextLine();
        System.out.println("Select Category of Product ");
        new CategoryView(product).showFormCategoryList();
        int categoryId = Integer.parseInt(Config.scanner.nextLine());

        product.setProductName(productName);
        product.setDescribe(describe);
        product.setPrice(price);
        product.setImg(image);
        product.setCategory(categoryController.detailCategory(categoryId));

        System.out.println("Update Product success !");
        System.out.println("---------------------------------------------------------------------------");
        menuProduct();
    }


    public void deleteProduct() {
        showAllListProduct();
        System.out.println("Input id Product!");
        int id = Integer.parseInt(Config.scanner.nextLine());
        productController.deleteProduct(id);
    }

    public void buyOrBack() {
        System.out.println("0.Back to Menu");
        System.out.println("1.Buy Now");
        System.out.println("2.View Detail");
        int choice = Integer.parseInt(Config.scanner.nextLine());
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
        new CartView().addProductToListCart(userLogin, orderProduct);
    }


    public void showListProductByCategory(int categoryId) {
        Category category = categoryController.detailCategory(categoryId);
        for (Product product : productList) {
            if (product.getCategory().getName().equals(category.getName())) {
                System.out.println("===================== Products ========================");
                System.out.println("----ID----Product----Describe----Image----Price----Category");
                System.out.println("----" + product.getId() + "----" + product.getProductName() + "----" + product.getDescribe() +
                        "----" + product.getImg() + "----" + product.getPrice() + " vnd" + "----" + product.getCategory().getName());
            }
        }
    }

    public void showAllListProduct() {
        System.out.println("===================== Products ========================");
        System.out.println("----ID----Product----Describe----Image----Price----Category");
        if (productList.size() == 0) {
            System.out.println("No product found ! ");
        } else {
            for (int i = 0; i < productList.size(); i++) {
                System.out.println("----" + productList.get(i).getId() + "----" + productList.get(i).getProductName() + "----" + productList.get(i).getDescribe() +
                        "----" + productList.get(i).getImg() + "----" + productList.get(i).getPrice() + " vnd" + "----" + productList.get(i).getCategory().getName());
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
        int id = Integer.parseInt(Config.scanner.nextLine());
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

    public static void main(String[] args) {
        new ProductView().buyProduct();
    }
}