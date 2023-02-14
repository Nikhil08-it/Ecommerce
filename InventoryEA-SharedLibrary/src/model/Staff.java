package model;

public class Staff {

    private Integer staffId;
    
    private String staffFname;

    private String staffLname;

    private String staffAddress;

    private String staffPass;

    public Staff() {
    }

    public Staff(Integer staffId, String staffFname, String staffLname, String staffAddress, String staffPass) {
        this.staffId = staffId;
        this.staffFname = staffFname;
        this.staffLname = staffLname;
        this.staffAddress = staffAddress;
        this.staffPass = staffPass;
    }

    public Staff(String staffFname, String staffLname, String staffAddress, String staffPass) {
        this.staffFname = staffFname;
        this.staffLname = staffLname;
        this.staffAddress = staffAddress;
        this.staffPass = staffPass;
    }
    
    public Staff(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffFname() {
        return staffFname;
    }

    public void setStaffFname(String staffFname) {
        this.staffFname = staffFname;
    }

    public String getStaffLname() {
        return staffLname;
    }

    public void setStaffLname(String staffLname) {
        this.staffLname = staffLname;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass;
    }

    @Override
    public String toString() {
        return "model.Staff[ staffId=" + staffId + " ]";
    }
    
}
