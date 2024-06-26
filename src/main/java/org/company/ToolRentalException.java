package org.company;

public class ToolRentalException extends Exception {
    public ToolRentalException() {
        super();
    }

    public ToolRentalException(String message) {
        super(message);
    }

    public ToolRentalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToolRentalException(Throwable cause) {
        super(cause);
    }
}
