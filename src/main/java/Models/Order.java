package Models;

/**
 * Immutable class for modeling rows of table Order.
 */
public class Order {
    private final int ID;
    private final int clientID;
    private final int productID;
    private final int quantity;
    private final String address;

    public Order ( int ID, int quantity, String address, int clientID, int productID ) {
        this.ID = ID;
        this.quantity = quantity;
        this.address = address;
        this.clientID = clientID;
        this.productID = productID;
    }

    public Order ( int ID, Client client, Product product, int quantity, String address ) {
        this.ID = ID;
        this.clientID = client.getID ( );
        this.productID = product.getID ( );
        this.quantity = quantity;
        this.address = address;
    }

    public Order ( Client client, Product product, int quantity, String address ) {
        this.ID = -1;
        this.clientID = client.getID ( );
        this.productID = product.getID ( );
        this.quantity = quantity;
        this.address = address;
    }

    public int getID ( ) {
        return ID;
    }

    public int getClientID ( ) {
        return clientID;
    }

    public int getProductID ( ) {
        return productID;
    }

    public int getQuantity ( ) {
        return quantity;
    }

    public String getAddress ( ) {
        return address;
    }
}
