package projectBase_00.model.aboutUs;

import java.io.Serializable;
import java.util.List;

public class AboutUs implements Serializable {
    private int id;
    private String title;
    private List<InfoCompany> infoCompanyList;
    public AboutUs() {
    }

    public AboutUs(int id, String title, List<InfoCompany> infoCompanyList) {
        this.id = id;
        this.title = title;
        this.infoCompanyList = infoCompanyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InfoCompany> getInfoCompanyList() {
        return infoCompanyList;
    }

    public void setInfoCompanyList(List<InfoCompany> infoCompanyList) {
        this.infoCompanyList = infoCompanyList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AboutUs{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", infoCompanyList=" + infoCompanyList +
                '}';
    }
}
