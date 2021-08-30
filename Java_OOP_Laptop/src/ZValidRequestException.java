public class ZValidRequestException extends Exception{
    public ZValidRequestException() {
        super("You have requested more than you can take.");   
    }
    public ZValidRequestException(String e)
    {
        super(e);
    }
}
