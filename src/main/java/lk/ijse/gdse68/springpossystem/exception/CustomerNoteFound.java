package lk.ijse.gdse68.springpossystem.exception;
/**
 * @author : sachini
 * @date : 2024-10-05
 **/
public class CustomerNoteFound extends RuntimeException{
    public CustomerNoteFound() {
        super();
    }

    public CustomerNoteFound(String message) {
        super(message);
    }

    public CustomerNoteFound(String message, Throwable cause) {
        super(message, cause);
    }
}
