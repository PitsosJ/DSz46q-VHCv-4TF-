import java.util.*;
public class Beneficiary extends User
{

    private int noPersons = 1;
    private ArrayList<RequestDonationList> receivedList = new ArrayList<RequestDonationList>();
    private ArrayList<Requests> requestsList = new ArrayList<Requests>();

    public Beneficiary(String name, String phone, int noPersons)
    {
        super.setName(name);
        super.setPhone(phone);
        this.noPersons = noPersons;
    }
    public int getNoPersons()
    {
        return noPersons;
    }
    public ArrayList<RequestDonationList> getReceivedList()
    {
        return receivedList;
    }
    public ArrayList<Requests> getRequestsList()
    {
        return requestsList;
    }
    public String info()
    {
        return "Beneficiary";
    }
    public void resetReceivedList()
    {
        receivedList.clear();
    }


    //wrapper methods
    public void add(Requests r)
    {    
        requestsList.add(r);
    }
    public void monitor()
    {
        for (Requests r: requestsList)
        {
            r.monitor();
        }
    }
    public void requestRemove(int index)
    {
        requestsList.remove(index);
    }
    public void commit(Organization org)
    {
        for (Requests r: requestsList)
        {
            r.commit(r, this, org);
        }
    }
}
