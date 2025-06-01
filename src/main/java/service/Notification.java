package service;

/**
 *
 * Notification is a class that uses the MessageService to send notifications to users.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Notification {
    private final MessageService service;

    public Notification(MessageService service) {
        this.service = service;
    }

    public void NotifyUser(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        service.sendMessage(message);
    }
}
