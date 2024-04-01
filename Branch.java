package com.branch;

public class Branch {
    private BranchName branchName;
    private String location;
    private int staffQuota;
/*    private List<Staff> staffMembers;
    private List<MenuItem> menu;
    private List<Order> orders; */

    public Branch(BranchName branchName, String location, int staffQuota) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = staffQuota;
//        this.staffMembers = new ArrayList<>();
//        this.menu = new ArrayList<>();
//        this.orders = new ArrayList<>();
    }
/*
    protected void addStaff(String staffID, String name, char role, String gender, int age, branchName branch) {
        Staff newStaff = new Staff(staffID, name, role, gender, age, branch);
        staffMembers.add(newStaff);
    }

    public void removeStaff(Staff staff) {
        staffMembers.remove(staff);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
*/
/*    protected List<MenuItem> loadMenuFromFile() {
        menu = new ArrayList<>();
        String path = "c:/";
        DataUtils.readCSV(path);
    }
*/

    public BranchName getBranchName() {
        return this.branchName;
    }

    public void setBranchName(BranchName branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStaffQuota() {
        return this.staffQuota;
    }

    public void setStaffQuota(int staffQuota) {
        this.staffQuota = staffQuota;
    }

    @Override
    public String toString() {
        return "{" +
            " branchName='" + getBranchName() + "'" +
            ", location='" + getLocation() + "'" +
            ", staffQuota='" + getStaffQuota() + "'" +
            "}";
    }

}
