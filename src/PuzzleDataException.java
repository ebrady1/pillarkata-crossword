public class PuzzleDataException extends Exception
{
    public PuzzleDataException()
    {
    }

    public PuzzleDataException(String message)
    {
        super(message);
    }

    public PuzzleDataException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PuzzleDataException(Throwable cause)
    {
        super(cause);
    }

    public PuzzleDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
