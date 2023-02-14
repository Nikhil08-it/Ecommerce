package model;

public class Admin {

    private Integer adminId;

    private String adminFname;

    private String adminLname;

    private String adminUsername;

    private String adminPass;


    public Admin() {
    }

    public Admin(Integer adminId, String adminFname, String adminLname, String adminUsername, String adminPass) {
        this.adminId = adminId;
        this.adminFname = adminFname;
        this.adminLname = adminLname;
        this.adminUsername = adminUsername;
        this.adminPass = adminPass;
    }

    public Admin(String adminFname, String adminLname, String adminUsername, String adminPass) {
        this.adminFname = adminFname;
        this.adminLname = adminLname;
        this.adminUsername = adminUsername;
        this.adminPass = adminPass;
    }
    

    public Admin(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminFname() {
        return adminFname;
    }

    public void setAdminFname(String adminFname) {
        this.adminFname = adminFname;
    }

    public String getAdminLname() {
        return adminLname;
    }

    public void setAdminLname(String adminLname) {
        this.adminLname = adminLname;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }


    @Override
    public String toString() {
        return "model.Admin[ adminId=" + adminId + " ]";
    }
    
}
