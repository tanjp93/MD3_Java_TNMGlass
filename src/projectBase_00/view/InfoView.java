package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.controller.InfoController;
import projectBase_00.model.aboutUs.InfoCompany;

import java.util.List;

public class InfoView {
    InfoController infoController=new InfoController();
    public InfoView() {
    }
    public void addInforDetail(){
        System.out.println("Add information");
        String information= Config.scanner.nextLine();
        System.out.println("Add image");
        String image= Config.scanner.nextLine();
        infoController.addInfoCompany(new InfoCompany(1,information,image));
    }
//    public List<InfoCompany> addInforDetail()
}
