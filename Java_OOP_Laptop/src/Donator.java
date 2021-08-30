import java.util.*;

public class Donator extends User
{
    public Donator(String name, String phone)
    {
        super.setName(name);
        super.setPhone(phone);
    }
    private ArrayList<Offers> offersList = new ArrayList<Offers>();
    
    public String info()
    {
        return "Donator";
    }

    //wrapper methods
    public void add(RequestDonation rd, Organization org)
    {
        Offers o = new Offers();
        o.add(rd, org);
        offersList.add(o);
    }
    public void monitor(Organization org)
    {
        for (Offers o: offersList)
        {
            System.out.println("123456");
            o.monitor();
        }
    }
    public void commit(Organization org)
    {
        for(Offers o: offersList)
        {
            o.commit(org);
        }
        offersList.clear();
        System.out.println("Commit was successful.");
    }
    public ArrayList<Offers> getOfferList()
    {
        return offersList;
    }
    
    public void remove(int index)
    {
        offersList.remove(index);
    }
}
