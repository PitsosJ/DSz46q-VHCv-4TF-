public class Material extends Entity
{
    private double level1;  //1 person
    private double level2;  //2-4
    private double level3;  //5+

    public Material(String name, String description, int id, double level1, double level2, double level3)
    {
        super.setName(name);
        super.setId(id);
        super.setDescription(description);
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }
    public double getLevel1()
    {
        return level1;
    }
    public double getLevel2()
    {
        return level2;
    }
    public double getLevel3()
    {
        return level3;
    }
    public String getDetails()
    {
        return "Material, lvl1:" + level1 + " , lvl2: " + level2 + " , lvl3: " + level3;
    }

    //added methods
    public double level(int level)
    {
        if (level == 1)
        {
            return level1;
        }
        else if (level == 2)
        {
            return level2;
        }
        else
        {
            return level3;
        }
    }
}
