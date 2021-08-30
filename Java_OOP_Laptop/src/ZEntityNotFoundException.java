public class ZEntityNotFoundException extends Exception{
    public ZEntityNotFoundException() {
        super("Entity not found in Entity List");   
    }
    public ZEntityNotFoundException(String e)
    {
        super(e);
    }
    
}
