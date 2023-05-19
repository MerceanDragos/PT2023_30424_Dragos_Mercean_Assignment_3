package Models;

import java.sql.Date;

/**
 * Immutable class for modeling rows of table Bill.
 */
public class Bill {
    private final int ID;
    private final int productID;
    private final int clientID;
    private final int quantity;
    private final Date date;
    private final float cost;

    public Bill ( int ID, int productID, int clientID, int quantity, Date date, float cost ) {
        this.ID = ID;
        this.productID = productID;
        this.clientID = clientID;
        this.quantity = quantity;
        this.date = date;
        this.cost = cost;
    }

    public Bill ( Client client, Product product, Order order ) {
        this.ID = -1;
        this.clientID = client.getID ( );
        this.productID = product.getID ( );
        this.cost = order.getQuantity ( ) * product.getCost ( );
        this.quantity = order.getQuantity ( );
        this.date = null;
    }

    public int getID ( ) {
        return ID;
    }

    public int getProductID ( ) {
        return productID;
    }

    public int getClientID ( ) {
        return clientID;
    }

    public int getQuantity ( ) {
        return quantity;
    }

    public Date getDate ( ) {
        return date;
    }

    public float getCost ( ) {
        return cost;
    }
}
