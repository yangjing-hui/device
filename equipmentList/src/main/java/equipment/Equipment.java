package equipment;

public class Equipment {
    private String sitename;
    private String name;
    private String number;
    private String classname;
    private String type;
    private String install_time;
    private String status;

    public Equipment(String sitename, String name, String number, String classname, String type, String install_time, String status) {
        this.sitename = sitename;
        this.name = name;
        this.number = number;
        this.classname = classname;
        this.type = type;
        this.install_time = install_time;
        this.status = status;
    }
    public Equipment(String info){
        String[] infoList = info.split(" ");
        this.sitename = infoList[0];
        this.name = infoList[1];
        this.number = infoList[2];
        this.classname = infoList[3];
        this.type = infoList[4];
        this.install_time = infoList[5] + " " + infoList[6];
        this.status = infoList[7];
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstall_time() {
        return install_time;
    }

    public void setInstall_time(String install_time) {
        this.install_time = install_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  sitename + ' ' +  name + ' ' + number + ' ' + classname + " " + type + ' ' + install_time + ' ' + status + "\n";
    }
}
