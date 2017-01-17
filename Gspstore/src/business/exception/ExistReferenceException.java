package business.exception;

public class ExistReferenceException extends Exception
{
    private String messageInfo = "";
    public ExistReferenceException(String message){
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
