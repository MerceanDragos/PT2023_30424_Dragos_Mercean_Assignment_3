package BLLs;

import DAO.BillDAO;
import Models.Bill;
import Models.Validators.BillCostValidator;
import Models.Validators.BillQuantityValidator;
import Models.Validators.Validator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Business-logic layer class for Bill table.
 * Handles data validity.
 */
public class BillBLL {
    private final ArrayList < Validator < Bill > > validators;

    public BillBLL ( ) {
        validators = new ArrayList < Validator < Bill > > ( );
        validators.add ( new BillQuantityValidator ( ) );
        validators.add ( new BillCostValidator ( ) );
    }

    public Bill[] findAll ( ) {
        return BillDAO.findAll ( );
    }

    public Bill find ( int ID ) throws NoSuchElementException {
        Bill client = BillDAO.findByID ( ID );
        if ( client == null )
            throw new NoSuchElementException ( "Bill with id = " + ID + " not found" );

        return client;
    }

    public Bill insert ( Bill bill ) throws IllegalArgumentException {
        for ( Validator < Bill > v : validators ) {
            v.validate ( bill );
        }

        return new Bill ( BillDAO.insert ( bill ), bill.getProductID ( ), bill.getClientID ( ), bill.getQuantity ( ), bill.getDate ( ), bill.getCost ( ) );
    }

}
