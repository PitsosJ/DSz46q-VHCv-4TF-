public class Main {
    public static void main(String[] args) throws Exception {
        Organization COV_AID = new Organization();
        COV_AID.addMaterial("milk","1L low fat",1001,4.0,10.0,15.0);
        COV_AID.addMaterial("sugar", "1KG", 1002, 1.0, 3.0, 5.0);
        COV_AID.addMaterial("rice", "500gr American", 1003,2.0, 4.0,7.0);
        COV_AID.addService("MedicalSupport", "Medical Support", 1004);
        COV_AID.addService("NurserySupport", "Nursery Support", 1005);
        COV_AID.addService("BabySitting", "Baby Sitting", 1006);
        COV_AID.setAdmin("John", "1");
        COV_AID.insertBeneficiary("Jack", "11", 4);
        COV_AID.insertBeneficiary("Eve", "12", 2);
        COV_AID.insertDonator("Mary", "21");
        Menu.init(COV_AID);
    }
}