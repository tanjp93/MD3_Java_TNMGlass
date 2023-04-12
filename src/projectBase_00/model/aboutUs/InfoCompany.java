package projectBase_00.model.aboutUs;

public class InfoCompany {
    private int id;
    private String info;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public InfoCompany() {
    }
    public InfoCompany(String info, String img) {
        this.info = info;
        this.img = img;
    }
    public InfoCompany(int id, String info, String img) {
        this.id = id;
        this.info = info;
        this.img = img;
    }

    @Override
    public String toString() {
        return "InfoCompany[" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", img='" + img + '\'' +
                ']';
    }
}
