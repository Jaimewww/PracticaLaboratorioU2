package service;

/**
 * MessageService is an interface that defines a method for sending messages.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public interface MessageService {
    void sendMessage(String message) throws IllegalArgumentException;
}
