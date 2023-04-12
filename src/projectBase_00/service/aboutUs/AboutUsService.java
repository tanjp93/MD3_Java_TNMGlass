package projectBase_00.service.aboutUs;

import projectBase_00.config.Config;
import projectBase_00.model.aboutUs.AboutUs;
import projectBase_00.model.aboutUs.InfoCompany;
import projectBase_00.model.cart.Cart;

import java.io.Serializable;
import java.util.List;

public class AboutUsService implements IAboutUs {
    List<AboutUs> aboutUsList = new Config<AboutUs>().readFromFile(Config.PATH_ABOUT_US);

    @Override
    public List findAll() {
        return aboutUsList;
    }

    @Override
    public void save(AboutUs aboutUs) {
        if (findById(aboutUs.getId())==null){
            aboutUsList.add(aboutUs);
        }else aboutUsList.set(aboutUsList.indexOf(findById(aboutUs.getId())),aboutUs);
        // Update
        new Config<AboutUs>().writeToFile(Config.PATH_ABOUT_US,aboutUsList );
    }

    @Override
    public AboutUs findById(int id) {
        if (aboutUsList.size() != 0) {
            return aboutUsList.get(id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (aboutUsList.size() != 0) {
            aboutUsList.remove(id);
        }
        new Config<AboutUs>().writeToFile(Config.PATH_ABOUT_US,aboutUsList );
    }
}
