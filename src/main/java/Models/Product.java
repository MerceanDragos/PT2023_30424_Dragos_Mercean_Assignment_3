package Models;

/**
 * Immutable class for modeling rows of table Product.
 */
public class Product {
    private final int ID;
    private final String name;
    private final float cost;
    private final int stock;

    public Product ( int ID, String name, float cost, int stock ) {
        this.ID = ID;
        this.name = name;
        this.cost = cost;
        this.stock = stock;
    }

    public Product ( String name, float cost, int stock ) {
        ID = -1;
        this.name = name;
        this.cost = cost;
        this.stock = stock;
    }

    public int getID ( ) {
        return ID;
    }

    public String getName ( ) {
        return name;
    }

    public float getCost ( ) {
        return cost;
    }

    public int getStock ( ) {
        return stock;
    }

}
