package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

/**
 * Controller class of a model-view-controller software design pattern.
 */
public class Controller implements ActionListener {

    private final Model model;

    public Controller ( Model model ) {
        this.model = model;
    }


    public void actionPerformed ( ActionEvent e ) {
        try {
            switch ( e.getActionCommand ( ) ) {
                case "Client" -> {
                    model.setSelectedTable ( Table.CLIENT );
                }
                case "Product" -> {
                    model.setSelectedTable ( Table.PRODUCT );
                }
                case "Order" -> {
                    model.setSelectedTable ( Table.ORDER );
                }
                case "Bill" -> {
                    model.setSelectedTable ( Table.BILL );
                }
                case "See all" -> {
                    model.seeAll ( );
                }
                case "Select by ID" -> {
                    model.displayIDInput ( "Select" );
                }
                case "Select ID" -> {
                    int id = model.view.getID ( );
                    model.selectByID ( id );
                }
                case "Insert" -> {
                    model.displayInsertInputs ( );
                }
                case "Input Insert" -> {
                    model.insert ( model.view.getInsertInputs ( ) );
                }
                case "Update" -> {
                    model.displayUpdateInputs ( );
                }
                case "Input Update" -> {
                    model.update ( model.view.getUpdateInputs ( ) );
                }
                case "Delete" -> {
                    model.displayIDInput ( "Delete" );
                }
                case "Delete ID" -> {
                    int id = model.view.getID ( );
                    model.delete ( id );
                }
                case "Hide message" -> {
                    model.view.hideMessage ( );
                }
            }
        } catch ( IllegalArgumentException | NoSuchElementException exception ) {
            model.view.displayMessage ( exception.getMessage ( ) );
        }
    }
}
