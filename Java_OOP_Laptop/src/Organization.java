import java.util.*;

public class Organization {
    private String name;
    private Admin admin;
    private ArrayList<Entity> entityList = new ArrayList<Entity>();
    private ArrayList<Donator> donatorList = new ArrayList<Donator>();
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    private ArrayList<RequestDonationList> currentDonations = new ArrayList<RequestDonationList>();

    Scanner scanner = new Scanner(System.in);
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAdmin(String name, String phone)
    {
        admin = new Admin(name, phone);
    }
    public Admin getAdmin()
    {
        return admin;
    }
    public void addEntity()
    {
        int selection = 0;
        while (selection !=  1 && selection != 2)
        {
            System.out.println("What type of entity would you like to add? \nType 1 for Material or 2 for Service and Press ENTER.");
            selection = scanner.nextInt();
            scanner.nextLine(); //new line dump
        }
        if (selection == 1)
        {
            int id;
            String name, description;
            double level1,level2,level3;
            boolean flag = false;
            System.out.println("Enter material's name: ");
            name = scanner.nextLine();
            System.out.println("Enter material's id: ");
            id = scanner.nextInt();
            try{
                for (Entity e: entityList)
                {
                    if (e.getId() == id)
                    {
                        flag = true;
                        throw new ZIDInUseException();
                        
                    }
                }
            }
            catch (ZIDInUseException e)
            {          
            }
            if (flag = false)
            {
                System.out.println("Enter material's description: ");
                description = scanner.nextLine();
                scanner.nextLine(); //new Line dump
                System.out.println("Enter Level1 amoun: ");
                level1 = scanner.nextDouble();
                System.out.println("Enter Level2 amount: ");
                level2 = scanner.nextDouble();
                System.out.println("Enter level3 amount: ");
                level3 = scanner.nextDouble();
                entityList.add(new Material (name, description, id, level1,level2,level3));
                scanner.nextLine(); //new Line dump
            }
        }
        else if (selection == 2)
        {
            int id;
            String name, description;
            boolean flag = false;
            System.out.println("Enter service's name: ");
            name = scanner.nextLine();
            System.out.println("Enter service's id: ");
            id = scanner.nextInt();
            try{
                for (Entity e: entityList)
                {
                    if (e.getId() == id)
                    {
                        flag = true;
                        throw new ZIDInUseException();
                        
                    }
                }
            }
            catch (ZIDInUseException e)
            {          
            }
            if (flag == false)
            {
                scanner.nextLine(); //new line dump
                System.out.println("Enter service's description: ");
                description = scanner.nextLine();
                entityList.add(new Service(name,id,description));
            }

        }
    
    }
    public void removeEntitiy()
    {   
        boolean found = false;
        if (entityList.size()>0)
        {
            System.out.println("Entity List:");
            for (Entity e: entityList)
            {
                System.out.println(e.getEntityInfo());
            }
            System.out.println("Please enter the ID of the entitiy you want to remove: ");
            int input = scanner.nextInt();
            for (Entity e: entityList)
            {
                if (e.getId() == input)
                {
                    found = true;
                    entityList.remove(entityList.indexOf(e)); // mesa sto remove prepei na mpei int pou  
                    //einai i thesi tou object sto list, to opoio pernoume me to arrayList.indexOf(e)
                    break;
                }
            }
        }
        else if(entityList.size() == 0)
        {
            System.out.println("Entity List is already empty.");
        }
        else if(entityList.size() < 0)
        {
            System.out.println("?");
        }
        if (found == false)
        {
            System.out.println("ID not found.");
        }
    }
    public void insertDonator(String name, String phone)
    {
        boolean phoneInUse = false;
        for (Donator u: donatorList)
        {
            if(u.getPhone() == phone)
            {
                phoneInUse = true;
            }
        }
        for (Beneficiary b: beneficiaryList)
        {
            if(b.getPhone() == phone)
            {
                phoneInUse = true;
            }
        }
        if (admin.getPhone() == phone)
        {
            phoneInUse = true;
        }
        if (phoneInUse == false)
        {
            donatorList.add(new Donator(name,phone));
        }
        else if(phoneInUse == true)
        {
            System.out.println("Phone number is already in use.");
        }
    }
    public void removeDonator()
    {
        System.out.println("Select the donator you want to remove, or press 0 to cancel.");
        int i = 1;
        for (Donator d: donatorList)
        {
            System.out.println(i+ ": " + d.getName() +", " + d.getPhone());
            i++;
        }
        int selection = scanner.nextInt();
        if (i > 0)
        {
            donatorList.remove(selection-1);
        }
        else 
        {
            System.out.println("removeDonator cancelled.");
        }

    }
    public void insertBeneficiary(String name, String phone, int noPersons)
    {
        boolean phoneInUse = false;
        for (Donator u: donatorList)
        {
            if(u.getPhone() == phone)
            {
                phoneInUse = true;
            }
        }
        for (Beneficiary b: beneficiaryList)
        {
            if(b.getPhone() == phone)
            {
                phoneInUse = true;
            }
        }
        if (admin.getPhone() == phone)
        {
            phoneInUse = true;
        }
        if (phoneInUse == false)
        {
            beneficiaryList.add(new Beneficiary(name,phone,noPersons));
        }
        else if(phoneInUse == true)
        {
            System.out.println("Phone number is already in use.");
        }
    }
    public void listEntities()
    {
        if(entityList.size() > 0)
        {
            System.out.println("Material List: ");
            for (Entity e: entityList)
            {
                if(e.getLevel1() >= 0.0)
                {  
                    System.out.println("\t" +e.getEntityInfo());
                }
            }
            System.out.println("Service List: ");
            for (Entity e: entityList)
            {
                if(e.getLevel1() == -1.0)
                {  
                    System.out.println("\t" +e.getEntityInfo());
                }
            }
        }
    }
    public void listBeneficiaries()
    {
        if(beneficiaryList.size() > 0)
        {
            for (Beneficiary b: beneficiaryList)
            {
                System.out.println("Name: "+b.getName()+", Phone: "+b.getPhone());
            }
        }
        else if (beneficiaryList.size() == 0)
        {
            System.out.println("There are no registered Beneficiaries.");
        }
    }
    public void listDonators()
    {
        if(donatorList.size() > 0)
        {
            for (Donator d: donatorList)
            {
                System.out.println("Name: "+d.getName()+", Phone: "+d.getPhone());
            }
        }
        else if(donatorList.size() == 0)
        {
            System.out.println("There are no registered Donators");
        }
    }
    

    //Added methods
    public ArrayList<Entity> getEntityList()
    {
        return entityList;
    }
    public ArrayList<RequestDonationList> getCurrentDonations()
    {
        return currentDonations;
    }
    public void addMaterial(String name, String description, int id, double level1, double level2, double level3)
    {
        entityList.add(new Material(name,description,id,level1,level2,level3));
    }
    public void addService(String name, String description, int id)
    {
        entityList.add(new Service(name,id,description));
    }   
    public ArrayList<Donator> getDonatorList()
    {
        return donatorList;
    }
    public ArrayList<Beneficiary> getBeneficiaryList()
    {
        return beneficiaryList;
    }    
    public double returnQuantity(int id, ArrayList<RequestDonationList> rdlA)
    {
        for (RequestDonationList rdl: rdlA)
        {
            if (rdl.get(id) != null)
            {
                return rdl.get(id).getQuantity();
            }
        }
        return 0;
    }
}
