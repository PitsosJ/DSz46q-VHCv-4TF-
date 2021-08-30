public class Admin extends User
{
    private boolean isAdmin = true;

    public Admin(String name, String phone)
    {
        super.setName(name);
        super.setPhone(phone);
    }
    public boolean returnAdmin()
    {
        return isAdmin;
    }
    public String info()
    {
        return "Admin";
    }
}
