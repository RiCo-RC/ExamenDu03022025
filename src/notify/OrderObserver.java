package notify;

import java.util.ArrayList;
import java.util.List;

public class OrderObserver {

    private List<INotify> notifications = new ArrayList<>();

    public void addObserver(INotify o) {
        this.notifications.add(o);
    }

    public void notify(String message) {
        for (INotify n : this.notifications) {
            n.update(message);
        }
    }
}
