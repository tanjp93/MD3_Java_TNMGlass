package projectBase_00.service.aboutUs;

import projectBase_00.config.Config;
import projectBase_00.model.aboutUs.InfoCompany;

import java.util.List;

public class InforService implements IInforService {
    List<InfoCompany> infoCompanyList = new Config<InfoCompany>().readFileText(Config.PATH_INFO_COMPANY);

    @Override
    public List<InfoCompany> findAll() {
        return infoCompanyList;
    }

    @Override
    public void save(InfoCompany infoCompany) {
        //add
        if (findById(infoCompany.getId()) == null) {
            infoCompanyList.add(infoCompany);
        } else
            infoCompanyList.set(infoCompanyList.indexOf(findById(infoCompany.getId())), infoCompany);
        new Config<InfoCompany>().writeFileText(Config.PATH_INFO_COMPANY, infoCompanyList);
    }

    @Override
    public InfoCompany findById(int id) {
        for (int i = 0; i < infoCompanyList.size(); i++) {
            if (infoCompanyList.get(i).getId() == id) {
                return infoCompanyList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (findById(id)!=null){
            infoCompanyList.remove(findById(id));
        }
        new Config<InfoCompany>().writeFileText(Config.PATH_INFO_COMPANY, infoCompanyList);
    }
}
