package Presentation;

import BLLs.*;
import Models.*;
import Models.ModelToolkit;

import java.util.NoSuchElementException;

/**
 * Model class of a model-view-controller software design pattern.
 */
public class Model {

    View view = new View ( new Controller ( this ) );

    private Table selectedTable = Table.CLIENT;

    private final ClientBLL clientBLL = new ClientBLL ( );
    private final ProductBLL productBLL = new ProductBLL ( );
    private final OrderBLL orderBLL = new OrderBLL ( );
    private final BillBLL billBLL = new BillBLL ( );


    public void setSelectedTable ( Table table ) {
        selectedTable = table;
        view.selectTable ( table );
    }

    public void seeAll ( ) {
        Object[] table = new Object[ 1 ];
        switch ( selectedTable ) {
            case CLIENT -> table = clientBLL.findAll ( );
            case PRODUCT -> table = productBLL.findAll ( );
            case ORDER -> table = orderBLL.findAll ( );
            case BILL -> table = billBLL.findAll ( );
        }
        view.displayTable ( ModelToolkit.getData ( table ), ModelToolkit.getColumnNames ( table ) );
    }

    public void selectByID ( int ID ) {
        Object[] table = new Object[ 1 ];
        switch ( selectedTable ) {
            case CLIENT -> table[ 0 ] = clientBLL.find ( ID );
            case PRODUCT -> table[ 0 ] = productBLL.find ( ID );
            case ORDER -> table[ 0 ] = orderBLL.find ( ID );
            case BILL -> table[ 0 ] = billBLL.find ( ID );
        }
        view.displayTable ( ModelToolkit.getData ( table ), ModelToolkit.getColumnNames ( table ) );
    }

    public void insert ( String[] inputs ) {
        try {
            switch ( selectedTable ) {
                case CLIENT -> {
                    Client client = new Client ( inputs[ 0 ], inputs[ 1 ] );
                    clientBLL.insert ( client );
                }
                case PRODUCT -> {
                    Product product = new Product ( inputs[ 0 ], Float.parseFloat ( inputs[ 1 ] ), Integer.parseInt ( inputs[ 2 ] ) );
                    productBLL.insert ( product );
                }
                case ORDER -> {
                    Order order = new Order ( -1, Integer.parseInt ( inputs[ 2 ] ), inputs[ 3 ], Integer.parseInt ( inputs[ 0 ] ), Integer.parseInt ( inputs[ 1 ] ) );
                    Product product = productBLL.find ( Integer.parseInt ( inputs[ 1 ] ) );
                    if ( Integer.parseInt ( inputs[ 2 ] ) > product.getStock ( ) )
                        orderBLL.insert ( order );
                    else
                        view.displayMessage ( "Insufficient stock" );
                }
                case BILL -> {
                    Bill bill = new Bill ( Integer.parseInt ( inputs[ 0 ] ), Integer.parseInt ( inputs[ 1 ] ), Integer.parseInt ( inputs[ 2 ] ), Integer.parseInt ( inputs[ 3 ] ), new java.sql.Date ( 2003, 4, 3 ), Float.parseFloat ( inputs[ 4 ] ) );
                    billBLL.insert ( bill );
                }
            }
        } catch ( IllegalArgumentException e ) {
            view.displayMessage ( e.getMessage ( ) );
        }
    }

    public void update ( String[] inputs ) {
        try {
            switch ( selectedTable ) {
                case CLIENT -> {
                    Client client = new Client ( Integer.parseInt ( inputs[ 0 ] ), inputs[ 1 ], inputs[ 2 ] );
                    clientBLL.update ( client );
                }
                case PRODUCT -> {
                    Product product = new Product ( Integer.parseInt ( inputs[ 0 ] ), inputs[ 1 ], Float.parseFloat ( inputs[ 2 ] ), Integer.parseInt ( inputs[ 3 ] ) );
                    productBLL.update ( product );
                }
                case ORDER -> {
                    Order order = new Order ( Integer.parseInt ( inputs[ 0 ] ), Integer.parseInt ( inputs[ 3 ] ), inputs[ 4 ], Integer.parseInt ( inputs[ 1 ] ), Integer.parseInt ( inputs[ 2 ] ) );
                    orderBLL.update ( order );
                }
            }
        } catch ( IllegalArgumentException | NoSuchElementException e ) {
            view.displayMessage ( e.getMessage ( ) );
        }
    }

    public void delete ( Object object ) {
        try {
            switch ( selectedTable ) {
                case CLIENT -> clientBLL.insert ( ( Client ) object );
                case PRODUCT -> productBLL.insert ( ( Product ) object );
                case ORDER -> orderBLL.insert ( ( Order ) object );
                case BILL -> billBLL.insert ( ( Bill ) object );
            }
        } catch ( IllegalArgumentException e ) {
            view.displayMessage ( e.getMessage ( ) );
        }
        view.displayMessage ( "" );
    }

    public void displayInsertInputs ( ) {
        view.displayInsertInputs ( selectedTable );
    }

    public void displayIDInput ( String operation ) {
        view.displayIDInput ( operation );
    }

    public void displayUpdateInputs ( ) {
        view.displayUpdateInputs ( selectedTable );
    }
}
