package business.exception;

public class StockLessBackNumException extends Exception
{
    private String messageInfo = "";
    public StockLessBackNumException(String message){
        this.messageInfo = message;
    }
    @Override
    public String getMessage()
    {
        return this.messageInfo;
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return this.messageInfo;
    }
    
}
