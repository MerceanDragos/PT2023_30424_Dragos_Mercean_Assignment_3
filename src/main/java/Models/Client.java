package Models;

/**
 * Immutable class for modeling rows of table Client.
 */
public class Client {
    private final int ID;
    private final String name;
    private final String email;

    public Client ( int ID, String name, String email ) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    public Client ( String name, String email ) {
        ID = -1;
        this.name = name;
        this.email = email;
    }

    public int getID ( ) {
        return ID;
    }

    public String getName ( ) {
        return name;
    }

    public String getEmail ( ) {
        return email;
    }
}
