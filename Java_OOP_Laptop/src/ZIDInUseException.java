public class ZIDInUseException extends Exception
{
    public ZIDInUseException() {
        super("ID is already in use. Please try again");   
    }
    public ZIDInUseException(String e)
    {
        super(e);
    }
}
