package exception;

/**
 *
 * Exception thrown when an entity is not found in the system.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {}
    public EntityNotFoundException(String message) {
        super(message);
    }
}
