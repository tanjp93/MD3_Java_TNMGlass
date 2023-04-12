package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.controller.AboutUsController;
import projectBase_00.model.aboutUs.AboutUs;

import java.util.List;

public class AboutUsView {
    AboutUsController aboutUsController = new AboutUsController();
    List<AboutUs> aboutUsList = aboutUsController.findAll();

    public AboutUsView() {
    }

    public void menuAboutUsView() {
        System.out.println("1. Company Information ");
        System.out.println("2. Add Information ");
        System.out.println("3. Edit Information ");
        System.out.println("4. Delete Information ");
        System.out.println("0. Back to Menu ");
        int choose = Integer.parseInt(Config.scanner.nextLine());
        switch (choose) {
            case 0:
                new Navbar();
                break;
            case 1:
                showAllInfo();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Please try again ! ");
                menuAboutUsView();
                break;
        }
    }

    public void showAllInfo() {
        System.out.println("--------------------About Us----------------");
        for (int i=aboutUsList.size()-1; i >= aboutUsList.size()-6||i >=0; i--) {
            aboutUsList.forEach(aboutUs -> {
                System.out.println("--------------------" + aboutUs.getTitle() + "----------------");
            });
            aboutUsList.get(i).getInfoCompanyList().forEach(infoCompany -> {
                System.out.println("----" + infoCompany.getId() + "----" + infoCompany.getInfo() + "----");
                System.out.println(infoCompany.getImg());
            });
        }
    }
    public void addInfo(){
        int idAboutUs=1;
        if (aboutUsList==null){
            idAboutUs=1;
        }else{
            idAboutUs=aboutUsList.get(aboutUsList.size()-1).getId()+1;
        }
        System.out.println("Input title : ");
        String title=Config.scanner.nextLine();
        new InfoView().addInforDetail();
        AboutUs newAboutUs=new AboutUs(idAboutUs,title,null);

    }
}
