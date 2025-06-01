package service;

/**
 *
 * ConsoleService is a simple implementation of the MessageService interface
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class ConsoleService implements MessageService {
    @Override
    public void sendMessage(String message) throws IllegalArgumentException {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        System.out.println(message);
    }
}