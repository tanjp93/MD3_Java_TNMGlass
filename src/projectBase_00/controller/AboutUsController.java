package projectBase_00.controller;

import projectBase_00.model.aboutUs.AboutUs;
import projectBase_00.service.aboutUs.AboutUsService;
import projectBase_00.service.aboutUs.IAboutUs;

import java.util.List;

public class AboutUsController extends AboutUsService {
    IAboutUs aboutUsService=new AboutUsService();

    public void addInfo(AboutUs aboutUs) {
        aboutUsService.save(aboutUs);
    }
    public void updateInfo(AboutUs aboutUs) {
        aboutUsService.save(aboutUs);
    };
    public AboutUs findInfoById(int id){
       return aboutUsService.findById(id);
    }
    public void deleteInfo(int id){
        aboutUsService.deleteById(id);
    }
}
