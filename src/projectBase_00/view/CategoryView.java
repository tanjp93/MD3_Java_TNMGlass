package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.controller.CategoryController;
import projectBase_00.controller.UserController;
import projectBase_00.dto.response.ResponseMessage;
import projectBase_00.model.category.Category;
import projectBase_00.model.product.Product;

import java.util.List;


public class CategoryView {
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();
    UserController userController = new UserController();

    public CategoryView(Product product) {
    }

    public CategoryView() {
        List<ResponseMessage> responseMessageList = userController.checkRole();
        System.out.println(responseMessageList);
//        while (true) {
        System.out.println("======================= Category  ===========================");
        System.out.println("1. List Category");
        if (responseMessageList.get(0).getMessage() != "user") {
            System.out.println("2. Create a new Category");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
        }
        System.out.println("5. All Product");
        System.out.println("Enter 0 to back to Menu !");
        int choiceCategory = Integer.parseInt(Config.scanner.nextLine());
        switch (choiceCategory) {
            case 1:
                showFormCategoryList();
                System.out.println("Show product in categories ");
                int categoryId=Integer.parseInt(Config.scanner.nextLine());
                new ProductView().showListProductByCategory(categoryId);
                new ProductView().buyOrBack();
                break;
            case 2:
                if (responseMessageList.get(0).getMessage() != "user") {
                    formCreateCategory();
                } else {
                    System.out.println("Invalid number, Please try again!");
                    new CategoryView();
                }
                break;
            case 3:
                if (responseMessageList.get(0).getMessage() != "user") {
                    formUpdateCategoryList();
                } else {
                    System.out.println("Input Invalid, Please try again!");
                    new CategoryView();
                }
                break;

            case 4:
                if (responseMessageList.get(0).getMessage() != "user") {
                    formDeleteCategoryList();
                } else {
                    System.out.println("Input Invalid, Please try again!");
                    new CategoryView();
                }
                break;
            case 5:
                new ProductView().showAllListProduct();
                System.out.println("Input Product Id");
                int id=Integer.parseInt(Config.scanner.nextLine());
                new ProductView().showListProductByCategory(id);
                break;
            case 0:
                new Navbar();
                break;
            default:
                System.out.println("Input Invalid, Please try again!");
                new CategoryView();
        }
    }


    public void showFormCategoryList() {
        System.out.println("=================== Category ========================");
        System.out.println("----ID----Product name----");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println("----" + categoryList.get(i).getId() + "----" + categoryList.get(i).getName());
        }
    }

    public void formCreateCategory() {
        int id = 0;
        if (categoryList.isEmpty()) {
            id = 1;
        } else {
            id = categoryList.get(categoryList.size() - 1).getId() + 1;
        }
        System.out.println("Enter the category name : ");
        String name = Config.scanner.nextLine();
        Category category = new Category(id, name);
        categoryController.createCategoryToDB(category);
        System.out.println("-----------------------------------------------------------------");
        new CategoryView();
    }

    public void formUpdateCategoryList() {
        showFormCategoryList();
        System.out.println("Enter Category Id need to Update");
        int id = Integer.parseInt(Config.scanner.nextLine());
        System.out.println("Enter Category name's update");
        String name = Config.scanner.nextLine();
        Category category = new Category(id, name);
        categoryController.updateCategory(category);
        System.out.println("-----------------------------------------------------------------");
        new CategoryView();
    }

    public void formDeleteCategoryList() {
        showFormCategoryList();
        System.out.println("Enter Category Id need to Update");
        int id = Integer.parseInt(Config.scanner.nextLine());
        categoryController.deleteCategory(id);
        System.out.println("-----------------------------------------------------------------");
        new CategoryView();
    }

    public Category findCategory(int id) {
        return categoryController.detailCategory(id);
    }

//    public
}
