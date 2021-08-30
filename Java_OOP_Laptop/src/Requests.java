import java.util.*;

public class Requests extends RequestDonationList
{
    public boolean validRequestDonation(RequestDonation rd, Beneficiary b)
    {
        double amountTaken = 0;
        if (rd.getEntity().getLevel1() >= 0) // ean einai material
        {
            if (b.getReceivedList().get(rd.getEntity().getId()) == null)
            {
                amountTaken = 0;
            }
            else if (b.getReceivedList().get(rd.getEntity().getId()) != null)
            {
                for (Requests r: b.getRequestsList())
                {
                    for(RequestDonation a: r.getRdEntities())
                    {
                        if(a.getEntity().getId() == rd.getEntity().getId())
                        {
                            amountTaken = a.getQuantity();
                        }
                    }
                }
            }
        
            if (b.getNoPersons() == 1)
            {
                if (amountTaken + rd.getQuantity() > rd.getEntity().level(1))
                {
                    System.out.println("Error! You requested for " + rd.getQuantity() + ", you can only get " + rd.getEntity().getLevel1() + " in total.");
                    return false;
                }
            }
            else if (b.getNoPersons() > 1 && b.getNoPersons() < 5)
            {
                if (amountTaken + rd.getQuantity() > rd.getEntity().level(2))
                {
                    System.out.println("Error! You requested for " + rd.getQuantity() + ", you can only get " + rd.getEntity().getLevel2() + " in total.");
                    return false;   
                }
            }
            else if (b.getNoPersons() > 5)
            {
                if (amountTaken + rd.getQuantity() > rd.getEntity().level(3))
                {
                    System.out.println("Error! You requested for " + rd.getQuantity() + ", you can only get " + rd.getEntity().getLevel3() + " in total.");
                    return false;   
                }
            }
            else
            {
                return true;
            }
        }
        return true; // ean einai material
    }

    public void add(RequestDonation rd, Beneficiary b, Organization org)
    {
        boolean isValid = false;
        boolean overload = true;
        try{

            for (RequestDonationList rdl : org.getCurrentDonations())
            {
                for (RequestDonation r: rdl.getRdEntities())
                {
                    if (rd.getQuantity() <= r.getQuantity())
                    {
                        overload = false;
                    }
                }
            }
            if (overload)
            {
                throw new ZRequestOverloadException();
            }
        }
        catch (ZRequestOverloadException e)
        {
        }
        try
        {
            if (validRequestDonation(rd, b))
            {
                isValid = true;
            }
            else {
                isValid = false;
                throw new ZValidRequestException();
            }
        }
        catch (ZValidRequestException e)
        {
        }
        if (isValid == true && overload == false)
        {
            b.getRequestsList().add(this);
        }
    }

    public void commit(Requests r, Beneficiary b, Organization org) //RequestDonation req) // prepei na ginei pali elegxos giati mporei na kanei 
    // gia paradeigma 2 requests pou na perasoun ton elegxo, enw mazi na 3epernane tin posotita 
    {
        boolean isValid = false;
        boolean overload = true;
        try {
            for (RequestDonationList currentDonations : org.getCurrentDonations())
            {
                for (RequestDonation DonationRD : currentDonations.getRdEntities())
                {
                    for (RequestDonation RequestRD : r.getRdEntities())
                    {
                        if(RequestRD.getEntity().getId() == DonationRD.getEntity().getId())
                        {
                            if (RequestRD.getQuantity() <= DonationRD.getQuantity())
                            {
                                overload = false;
                            }
                        }   
                    }
                }
            }
            if (overload = true)
            {
                throw new ZRequestOverloadException(); 
            }

        }
        catch (ZRequestOverloadException e)
        {}
        try
        {
            for (RequestDonation rd: r.getRdEntities())
            {
                    if (validRequestDonation(rd, b))
                    {
                        isValid = true;
                    }
            }
            
            if (isValid == false)
            {
                throw new ZValidRequestException();
            }
        }
        catch (ZValidRequestException e)
        {}
        if (isValid == true && overload == false)
        {
            b.getReceivedList().add(r);
            for (RequestDonationList currentDonations: org.getCurrentDonations())
            {
                for (RequestDonation DonationRD: currentDonations.getRdEntities())
                {
                    for (RequestDonationList currentRequests : b.getRequestsList())
                    {
                        for (RequestDonation RequestRD : currentRequests.getRdEntities())
                        {
                            if(RequestRD.getEntity().getId() == DonationRD.getEntity().getId())
                            {
                                DonationRD.setQuantity(DonationRD.getQuantity() - RequestRD.getQuantity());
                            }
                        }
                    }
                }
            }
        }
    }
}