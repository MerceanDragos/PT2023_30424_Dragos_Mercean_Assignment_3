package BLLs;

import DAO.OrderDAO;
import Models.Order;
import Models.Validators.OrderQuantityValidator;
import Models.Validators.Validator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Business-logic layer class for Order table.
 * Handles data validity.
 */
public class OrderBLL {
    private final ArrayList < Validator < Order > > validators;

    public OrderBLL ( ) {
        validators = new ArrayList < Validator < Order > > ( );
        validators.add ( new OrderQuantityValidator ( ) );
    }

    public Order[] findAll ( ) {
        return OrderDAO.findAll ( );
    }

    public Order find ( int ID ) throws NoSuchElementException {
        Order client = OrderDAO.findByID ( ID );
        if ( client == null )
            throw new NoSuchElementException ( "Order with id = " + ID + " not found" );

        return client;
    }

    public Order insert ( Order order ) throws IllegalArgumentException {
        for ( Validator < Order > v : validators ) {
            v.validate ( order );
        }

        return new Order ( OrderDAO.insert ( order ), order.getQuantity ( ), order.getAddress ( ), order.getProductID ( ), order.getClientID ( ) );
    }

    public void update ( Order order ) throws IllegalArgumentException, NoSuchElementException {
        for ( Validator < Order > v : validators ) {
            v.validate ( order );
        }

        OrderDAO.update ( order );
    }

    public void delete ( Order order ) throws NoSuchElementException {
        OrderDAO.delete ( order );
    }
}
