package notify;

public class Customer implements INotify {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("--> " + this.name +
                " a reçu une nouvelle notification :\n" +
                ">>> " + message);
    }
}
