package projectBase_00.controller;

import projectBase_00.model.aboutUs.InfoCompany;
import projectBase_00.service.aboutUs.IInforService;
import projectBase_00.service.aboutUs.InforService;

import java.util.List;

public class InfoController extends InforService {
    IInforService inforService=new InforService();
    public List showAllListInfo(){
      return   inforService.findAll();
    }
    public void addInfoCompany (InfoCompany infoCompany){
        inforService.save(infoCompany);
    }
    public void updateInfoCompany (InfoCompany infoCompany){
        inforService.save(infoCompany);
    }
    public void deleteInfo(int id){
        inforService.deleteById(id);
    }
}
