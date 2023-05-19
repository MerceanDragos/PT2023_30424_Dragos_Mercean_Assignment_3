package Models.Validators;

import Models.Bill;

/**
 * Validator that assures positive bill cost.
 */
public class BillCostValidator implements Validator < Bill > {

    public BillCostValidator ( ) {

    }

    public void validate ( Bill bill ) throws IllegalArgumentException {
        if ( !( bill.getCost ( ) > 0 ) )
            throw new IllegalArgumentException ( "Invalid bill cost!" );
    }
}
