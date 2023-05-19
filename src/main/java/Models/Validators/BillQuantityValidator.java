package Models.Validators;

import Models.Bill;

/**
 * Validator that assures positive bill quantity.
 */
public class BillQuantityValidator implements Validator < Bill > {

    public BillQuantityValidator ( ) {

    }

    public void validate ( Bill bill ) throws IllegalArgumentException {
        if ( ! (bill.getQuantity () > 0) )
            throw new IllegalArgumentException ( "Invalid bill quantity!" );
    }
}
