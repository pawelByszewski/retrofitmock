package eu.softisland.retrofit.model.login;

public class EchoResponse {

    private String message;
    private int quantity;

    public String getMessage() {
        return message;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "EchoResponse{\n"
                + "\tmessage='" + message + "\'\n"
                + "\tquantity=" + quantity + "\n"
                + '}';
    }
}
