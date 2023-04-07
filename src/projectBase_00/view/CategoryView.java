package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.controller.CategoryController;
import projectBase_00.model.category.Category;

import java.util.List;

public class CategoryView {
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();

    public CategoryView() {
//        while (true) {
            System.out.println("======================= Category  ===========================");
            System.out.println("1. List Category");
            System.out.println("2. Create a new Category");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. All Product");
            System.out.println("Enter 0 to back to Menu !");
            int choiceCategory = Integer.parseInt(Config.scanner.nextLine());
            switch (choiceCategory) {
                case 1:
                    showFormCategoryList();
                    break;
                case 2:
                    formCreateCategory();
                    break;
                case 3:
                    formUpdateCategoryList();
                    break;
                case 4:
                    formDeleteCategoryList();
                    break;
                case 5:
                    new ProductView().showAllListProduct();
                    break;
                case 0:
                    new Navbar();
                    break;
                default:
                    System.out.println("Input Invalid, Please try again!");
                    choiceCategory = Integer.parseInt(Config.scanner.nextLine());
            }
//        }
    }


    public void showFormCategoryList() {
        System.out.println("=================== List Products ========================");
        System.out.println("----ID----Product name----");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println("----" + categoryList.get(i).getId() + "----" + categoryList.get(i).getName());
        }
    }
    public void formCreateCategory(){
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
}
