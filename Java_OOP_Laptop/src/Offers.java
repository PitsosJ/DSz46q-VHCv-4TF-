public class Offers extends RequestDonationList
{

    public void commit(Organization org)
    {
        org.getCurrentDonations().add(this);
    }
    public void remove(int index)
    {
        super.remove(index);
    }
}
