package lk.ijse.gdse68.springpossystem.exception;

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
