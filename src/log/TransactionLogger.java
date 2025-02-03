package log;

public class TransactionLogger {
    private String message;

    public void log(String message) {
        System.out.println("Transaction : " + message);
    }
}