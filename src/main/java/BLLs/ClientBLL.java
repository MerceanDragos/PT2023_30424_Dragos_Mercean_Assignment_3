package BLLs;

import DAO.ClientDAO;
import Models.Client;
import Models.Validators.ClientEmailValidator;
import Models.Validators.ClientNameValidator;
import Models.Validators.Validator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Business-logic layer class for Client table.
 * Handles data validity.
 */
public class ClientBLL {
    private final ArrayList < Validator < Client > > validators;

    public ClientBLL ( ) {
        validators = new ArrayList < Validator < Client > > ( );
        validators.add ( new ClientNameValidator ( ) );
        validators.add ( new ClientEmailValidator ( ) );
    }

    public Client[] findAll ( ) {
        return ClientDAO.findAll ( );
    }

    public Client find ( int ID ) throws NoSuchElementException {
        Client client = ClientDAO.findByID ( ID );
        if ( client == null )
            throw new NoSuchElementException ( "Client with id = " + ID + " not found" );

        return client;
    }

    public Client insert ( Client client ) throws IllegalArgumentException {
        for ( Validator < Client > v : validators ) {
            v.validate ( client );
        }

        return new Client ( ClientDAO.insert ( client ), client.getName ( ), client.getEmail ( ) );
    }

    public void update ( Client client ) throws IllegalArgumentException, NoSuchElementException {
        for ( Validator < Client > v : validators ) {
            v.validate ( client );
        }

        ClientDAO.update ( client );
    }

    public void delete ( Client client ) throws NoSuchElementException {
        ClientDAO.delete ( client );
    }

}