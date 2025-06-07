package knihovna.data;

/**
 * Výjimka při načítání a ukládání dat do a ze souboru
 */

public class PersistenceException extends Exception {
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
