public class RequestDonation
{
    private Entity entity;
    private double quantity;
    
    public Entity getEntity()
    {
        return entity;
    }
    public double getQuantity()
    {
        return quantity;
    }
    public void setQuantity(double quantity)
    {
        this.quantity = quantity;
    }
    public RequestDonation(Entity entity, double quantity)
    {
        this.entity = entity;
        this.quantity = quantity;
    }

    //Comparator
    public boolean compare(RequestDonation rd)
    {
        if (this.getEntity().getId() == rd.getEntity().getId())
        {
            return true;
        }
        else {
            return false;
        }
    }
}