import java.util.*;

public class RequestDonationList {

    public static ArrayList<RequestDonation> rdEntities = new ArrayList<RequestDonation>();

    public RequestDonation get(int id)
    {
        for (RequestDonation rd: rdEntities)
        {
            if (rd.getEntity().getId() == id)
            {
                return rd;
            }
        }
        return null;
    }
    public void add(RequestDonation rd, Organization org)
    {   
        boolean entityInEntityList = false;                   
        for (Entity e: org.getEntityList())    
        {                                               
            if(rd.getEntity() == e)                     
            {                                           
                entityInEntityList = true;                    
            }                                           
        }                                               
        try{                                            
            if (entityInEntityList == false)                  
            {                                       
                throw new ZEntityNotFoundException();
            }
        }
        catch (ZEntityNotFoundException e)
        {
        }
        if (entityInEntityList == true)
        {
            boolean entityInrd = false; 
            for (RequestDonation r: rdEntities)
            {
                if (r.compare(rd) == true)
                {
                    entityInrd = true;
                    r.setQuantity(rd.getQuantity() + r.getQuantity()); // to entity pou einai mesa sto rd ginetai modify
                }
            }
            if (entityInrd == false)
            {
                rdEntities.add(rd);
            }
        }
    }
    public void remove(int id)
    {
        if (rdEntities.get(id) == null)
        {
            System.out.println("Entity at index not found.");
        }
        else {
            rdEntities.remove(id);
        }
    }
    public void modify(int id, double quantity)
    {
        boolean entityFound = false;
        for (RequestDonation r: rdEntities)
        {
            if (r.getEntity().getId() == id)
            {
                entityFound = true;
                r.setQuantity(quantity);
            }
        }
        if (entityFound == false)
        {
            System.out.println("Entity not found.");
        }
    } 
    public void monitor()
    {
        for (RequestDonation rd: rdEntities)
        {
            System.out.println("ID: " + rd.getEntity().getId()+", Name: " + rd.getEntity().getName() + ", Quantity: " + rd.getQuantity());
        }
    }
    public void reset()
    {
        rdEntities.clear();
    }

    public ArrayList<RequestDonation> getRdEntities()
    {
        return rdEntities;
    }
}