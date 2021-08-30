public class ZRequestOverloadException extends Exception
{
    public ZRequestOverloadException() {
        super("You requested for an amount that is not available in the Organization");   
    }
    public ZRequestOverloadException(String e)
    {
        super(e);
    }
}