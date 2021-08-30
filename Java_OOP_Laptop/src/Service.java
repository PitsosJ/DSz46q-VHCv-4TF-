public class Service extends Entity
{
    public Service(String name, int id, String description)
    {
        super.setName(name);
        super.setId(id);
        super.setDescription(description);
    }
    public String getDetails()
    {
        return "Service";
    }
    public double getLevel1() // gia na ginei diaxwrismos metaxi service kai materials
    {
        return -1.0;
    }
    public double getLevel2()
    {
        return -1.0;
    }
    public double getLevel3()
    {
        return -1.0;
    }
    public double level(int level)
    {
        return -1.0;
    }
}