abstract public class Entity {
    private String name;
    private String description;
    private int id;

    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public int getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setDescription(String descriiption)
    {
        this.description = descriiption;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getEntityInfo()
    {
        return id + ": " +name + ", " + description;
    }
    abstract String getDetails();

    @Override
    public String toString()
    {
        return getEntityInfo() + ", " + getDetails();
    }
    abstract double getLevel1(); // gia na ginei diaxwrismos metaxi service kai materials
    abstract double getLevel2();
    abstract double getLevel3();
    abstract double level(int level);
}
