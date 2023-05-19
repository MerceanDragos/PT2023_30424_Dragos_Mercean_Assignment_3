package Presentation;

import javax.swing.*;
import java.awt.*;

/**
 * View class of a model-view-controller software design pattern.
 */
public class View extends JFrame {

    private JPanel mainPanelBuilder ( Controller controller ) {
        FlowLayout layout = new FlowLayout ( FlowLayout.CENTER, 100, 100 );
        JPanel mainPanel = new JPanel ( layout );

        mainPanel.setBackground ( Color.DARK_GRAY );
        mainPanel.setPreferredSize ( new Dimension ( 800, 340 ) );

        tableButtons = new JButton[ 4 ];

        for ( int i = 0; i < 4; i++ ) {
            tableButtons[ i ] = new JButton ( );
            tableButtons[ i ].setFocusable ( false );
            tableButtons[ i ].setForeground ( Color.WHITE );
            tableButtons[ i ].setBackground ( Color.GRAY );
            mainPanel.add ( tableButtons[ i ] );
        }

        tableButtons[ 0 ].setText ( "Clients✅︎" );
        tableButtons[ 0 ].setActionCommand ( "Client" );
        tableButtons[ 1 ].setText ( "Products" );
        tableButtons[ 1 ].setActionCommand ( "Product" );
        tableButtons[ 2 ].setText ( "Orders" );
        tableButtons[ 2 ].setActionCommand ( "Order" );
        tableButtons[ 3 ].setText ( "Bills" );
        tableButtons[ 3 ].setActionCommand ( "Bill" );

        for ( JButton button : tableButtons ) {
            button.addActionListener ( controller );
            button.setFocusable ( false );
            button.setBackground ( Color.GRAY );
        }

        controlButtons = new JButton[ 5 ];
        JPanel controlPanel = new JPanel ( );
        controlPanel.setLayout ( new BoxLayout ( controlPanel, BoxLayout.LINE_AXIS ) );
        for ( int i = 0; i < 5; i++ ) {
            controlButtons[ i ] = new JButton ( );
            controlButtons[ i ].setFocusable ( false );
            controlButtons[ i ].addActionListener ( controller );
            controlButtons[ i ].setBackground ( Color.GRAY );
            controlPanel.add ( controlButtons[ i ] );
        }

        controlButtons[ 0 ].setText ( "See all" );
        controlButtons[ 0 ].setActionCommand ( "See all" );
        controlButtons[ 1 ].setText ( "Select by ID" );
        controlButtons[ 1 ].setActionCommand ( "Select by ID" );
        controlButtons[ 2 ].setText ( "Insert" );
        controlButtons[ 2 ].setActionCommand ( "Insert" );
        controlButtons[ 3 ].setText ( "Update" );
        controlButtons[ 3 ].setActionCommand ( "Update" );
        controlButtons[ 4 ].setText ( "Delete" );
        controlButtons[ 4 ].setActionCommand ( "Delete" );

        mainPanel.add ( controlPanel );

        return mainPanel;
    }

    private JFrame infoFrameBuilder ( Controller controller ) {
        JButton okButton = new JButton ( "Ok" );
        messageLabel = new JLabel ( "message" );
        JPanel messagePanel = new JPanel ( new FlowLayout ( FlowLayout.CENTER, 500, 40 ) );
        messageFrame = new JFrame ( );

        messagePanel.setBackground ( Color.DARK_GRAY );
        messagePanel.setPreferredSize ( new Dimension ( 300, 180 ) );

        messageLabel.setAlignmentX ( Component.CENTER_ALIGNMENT );
        messageLabel.setForeground ( Color.WHITE );

        okButton.setAlignmentX ( Component.CENTER_ALIGNMENT );
        okButton.setBackground ( Color.GRAY );
        okButton.setFocusable ( false );
        okButton.addActionListener ( controller );
        okButton.setActionCommand ( "Hide message" );

        messagePanel.add ( messageLabel );
        messagePanel.add ( okButton );

        messageFrame.setDefaultCloseOperation ( JFrame.HIDE_ON_CLOSE );
        messageFrame.add ( messagePanel );
        messageFrame.pack ( );
        messageFrame.setResizable ( true );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        messageFrame.setLocation ( ( screenSize.width - messageFrame.getWidth ( ) ) / 2, ( screenSize.height - messageFrame.getHeight ( ) ) / 2 );
//        infoFrame.setVisible ( true );

        return messageFrame;
    }

    private JFrame tableFrameBuilder ( ) {
        Object[] columnNames = new Object[ 6 ];
        Object[][] data = new Object[ 30 ][ 6 ];

        for ( int i = 1; i <= 6; i++ )
            columnNames[ i - 1 ] = "column " + i;

        for ( int i = 1; i <= 30; i++ )
            for ( int j = 1; j <= 6; j++ )
                data[ i - 1 ][ j - 1 ] = i + ":" + j;


        JTable table = new JTable ( data, columnNames );
        scrollPane = new JScrollPane ( );
        scrollPane.setBackground ( Color.DARK_GRAY );
        scrollPane.setViewportView ( table );
        JPanel tablePanel = new JPanel ( );


        tablePanel.setBackground ( Color.DARK_GRAY );
        tableFrame = new JFrame ( );

        tablePanel.add ( scrollPane );
        tableFrame.add ( tablePanel );

        tableFrame.setDefaultCloseOperation ( JFrame.HIDE_ON_CLOSE );
        tableFrame.pack ( );
        tableFrame.setResizable ( false );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        tableFrame.setLocation ( ( screenSize.width - tableFrame.getWidth ( ) ) / 2, ( screenSize.height - tableFrame.getHeight ( ) ) / 2 );
//        tableFrame.setVisible ( true );

        return tableFrame;
    }

    private JFrame inputFrameBuilder ( Controller controller ) {
        inputLabelsPanel = new JPanel ( new FlowLayout ( FlowLayout.CENTER ) );
        inputTextFieldsPanel = new JPanel ( new FlowLayout ( FlowLayout.CENTER ) );
        inputButton = new JButton ( "Input" );
        JPanel inputPanel = new JPanel ( new FlowLayout ( FlowLayout.CENTER, 500, 20 ) );
        JFrame inputFrame = new JFrame ( );

        inputPanel.setBackground ( Color.DARK_GRAY );
        inputPanel.setPreferredSize ( new Dimension ( 600, 180 ) );

        inputButton.setBackground ( Color.GRAY );
        inputButton.setFocusable ( false );
        inputButton.addActionListener ( controller );
        inputButton.setActionCommand ( "Insert" );

        inputLabelsPanel.setBackground ( Color.DARK_GRAY );
        inputLabelsPanel.setPreferredSize ( new Dimension ( 600, 35 ) );

        inputTextFieldsPanel.setBackground ( Color.DARK_GRAY );
        inputTextFieldsPanel.setPreferredSize ( new Dimension ( 600, 35 ) );

        inputPanel.add ( inputLabelsPanel );
        inputPanel.add ( inputTextFieldsPanel );
        inputPanel.add ( inputButton );

        inputFrame.add ( inputPanel );
        inputFrame.setDefaultCloseOperation ( JFrame.DO_NOTHING_ON_CLOSE );
        inputFrame.pack ( );
        inputFrame.setResizable ( false );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        inputFrame.setLocation ( ( screenSize.width - inputFrame.getWidth ( ) ) / 2, ( screenSize.height - inputFrame.getHeight ( ) ) / 2 );
//        inputFrame.setVisible ( true );

        return inputFrame;
    }

    private JButton[] tableButtons;
    private JButton[] controlButtons;

    private JFrame messageFrame;
    private JLabel messageLabel;

    private JFrame tableFrame;
    private JScrollPane scrollPane;

    private final JFrame inputFrame;
    private JPanel inputLabelsPanel;
    private JPanel inputTextFieldsPanel;
    private JButton inputButton;

    public View ( Controller controller ) {
        JPanel mainPanel = mainPanelBuilder ( controller );
        messageFrame = infoFrameBuilder ( controller );
        tableFrame = tableFrameBuilder ( );
        inputFrame = inputFrameBuilder ( controller );
        setTitle ( "Order Management" );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );

        add ( mainPanel );

        pack ( );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        setResizable ( false );
        setLocation ( ( screenSize.width - getWidth ( ) ) / 2, ( screenSize.height - getHeight ( ) ) / 2 );
        setVisible ( true );
    }

    private void hideControlButtons ( ) {
        controlButtons[ 3 ].setVisible ( false );
        controlButtons[ 4 ].setVisible ( false );
    }

    private void displayControlButtons ( ) {
        controlButtons[ 3 ].setVisible ( true );
        controlButtons[ 4 ].setVisible ( true );
    }

    private void unselectTables ( ) {
        tableButtons[ 0 ].setText ( "Clients" );
        tableButtons[ 1 ].setText ( "Products" );
        tableButtons[ 2 ].setText ( "Orders" );
        tableButtons[ 3 ].setText ( "Bills" );
    }

    public void selectTable ( Table table ) {
        unselectTables ( );
        switch ( table ) {
            case CLIENT -> {
                tableButtons[ 0 ].setText ( "Clients✅︎" );
                displayControlButtons ( );
            }
            case PRODUCT -> {
                tableButtons[ 1 ].setText ( "Products✅︎" );
                displayControlButtons ( );
            }
            case ORDER -> {
                tableButtons[ 2 ].setText ( "Orders✅︎" );
                displayControlButtons ( );
            }
            case BILL -> {
                tableButtons[ 3 ].setText ( "Bills✅︎" );
                hideControlButtons ( );
            }
        }
        revalidate ( );
        repaint ( );
    }

    private void updateMessage ( String message ) {
        messageFrame.setVisible ( false );
        messageLabel.setText ( message );
        messageFrame.pack ( );
    }

    public void displayMessage ( String message ) {
        updateMessage ( message );
        messageFrame.setVisible ( true );
    }

    public void hideMessage ( ) {
        messageFrame.setVisible ( false );
    }

    public void displayTable ( Object[][] data, Object[] columnNames ) {
        tableFrame.setVisible ( false );
        if ( data != null ) {
            hideMessage ( );
            scrollPane.setViewportView ( new JTable ( data, columnNames ) );
            tableFrame.pack ( );
            Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
            tableFrame.setLocation ( ( screenSize.width - tableFrame.getWidth ( ) ) / 2, ( screenSize.height - tableFrame.getHeight ( ) ) / 2 );
            tableFrame.setVisible ( true );
        } else {
            displayMessage ( "Empty table" );
        }
    }

    public void displayIDInput ( String operation ) {
        for ( JButton button : tableButtons )
            button.setEnabled ( false );

        inputFrame.setVisible ( false );
        inputLabelsPanel.removeAll ( );
        inputTextFieldsPanel.removeAll ( );

        JLabel idLabel = new JLabel ( "ID" );
        idLabel.setForeground ( Color.WHITE );
        inputLabelsPanel.add ( idLabel );

        JTextField idTextField = new JTextField ( );
        idTextField.setPreferredSize ( new Dimension ( 100, 20 ) );
        inputTextFieldsPanel.add ( idTextField );

        inputButton.setActionCommand ( operation + " ID" );
        inputButton.setText ( operation );

        inputFrame.pack ( );
        inputFrame.setResizable ( false );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        inputFrame.setLocation ( ( screenSize.width - inputFrame.getWidth ( ) ) / 2, ( screenSize.height - inputFrame.getHeight ( ) ) / 2 );
        inputFrame.setVisible ( true );
    }

    public Integer getID ( ) {
        inputFrame.setVisible ( false );
        for ( JButton button : tableButtons )
            button.setEnabled ( true );
        return Integer.valueOf ( ( ( JTextField ) inputTextFieldsPanel.getComponent ( 0 ) ).getText ( ) );
    }

    public void displayInsertInputs ( Table table ) {
        for ( JButton button : tableButtons )
            button.setEnabled ( false );

        inputFrame.setVisible ( false );
        inputLabelsPanel.removeAll ( );
        inputTextFieldsPanel.removeAll ( );

        inputButton.setActionCommand ( "Input Insert" );
        inputButton.setText ( "Insert" );

        switch ( table ) {

            case CLIENT -> {
                JLabel[] inputLabels = new JLabel[ 2 ];
                JTextField[] inputTextFields = new JTextField[ 2 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 80 );

                for ( int i = 0; i < 2; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "name" );
                inputLabels[ 1 ].setText ( "email" );
            }

            case PRODUCT -> {
                JLabel[] inputLabels = new JLabel[ 3 ];
                JTextField[] inputTextFields = new JTextField[ 3 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 80 );

                for ( int i = 0; i < 3; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "name" );
                inputLabels[ 1 ].setText ( "cost" );
                inputLabels[ 2 ].setText ( "stock" );
            }

            case ORDER -> {
                JLabel[] inputLabels = new JLabel[ 4 ];
                JTextField[] inputTextFields = new JTextField[ 4 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 60 );

                for ( int i = 0; i < 4; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "clientID" );
                inputLabels[ 1 ].setText ( "productID" );
                inputLabels[ 2 ].setText ( "quantity" );
                inputLabels[ 3 ].setText ( "address" );
            }

            case BILL -> {
                JLabel[] inputLabels = new JLabel[ 5 ];
                JTextField[] inputTextFields = new JTextField[ 5 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 65 );

                for ( int i = 0; i < 5; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "ID" );
                inputLabels[ 1 ].setText ( "productID" );
                inputLabels[ 2 ].setText ( "clientID" );
                inputLabels[ 3 ].setText ( "quantity" );
                inputLabels[ 4 ].setText ( "cost" );
            }
        }

        inputFrame.pack ( );
        inputFrame.setResizable ( false );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        inputFrame.setLocation ( ( screenSize.width - inputFrame.getWidth ( ) ) / 2, ( screenSize.height - inputFrame.getHeight ( ) ) / 2 );
        inputFrame.setVisible ( true );
    }

    public String[] getInsertInputs ( ) {
        inputFrame.setVisible ( false );
        int noInputs = inputTextFieldsPanel.getComponentCount ( );

        String[] inputs = new String[ noInputs ];
        for ( int i = 0; i < noInputs; i++ ) {
            inputs[ i ] = ( ( JTextField ) inputTextFieldsPanel.getComponent ( i ) ).getText ( );
        }
        for ( JButton button : tableButtons )
            button.setEnabled ( true );

        return inputs;
    }

    public void displayUpdateInputs ( Table table ) {
        for ( JButton button : tableButtons )
            button.setEnabled ( false );

        inputFrame.setVisible ( false );
        inputLabelsPanel.removeAll ( );
        inputTextFieldsPanel.removeAll ( );

        inputButton.setActionCommand ( "Input Update" );
        inputButton.setText ( "Update" );

        switch ( table ) {

            case CLIENT -> {
                JLabel[] inputLabels = new JLabel[ 3 ];
                JTextField[] inputTextFields = new JTextField[ 3 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 80 );

                for ( int i = 0; i < 3; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "ID" );
                inputLabels[ 1 ].setText ( "name" );
                inputLabels[ 2 ].setText ( "email" );
            }

            case PRODUCT -> {
                JLabel[] inputLabels = new JLabel[ 4 ];
                JTextField[] inputTextFields = new JTextField[ 4 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 80 );

                for ( int i = 0; i < 4; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "ID" );
                inputLabels[ 1 ].setText ( "name" );
                inputLabels[ 2 ].setText ( "cost" );
                inputLabels[ 3 ].setText ( "stock" );
            }

            case ORDER -> {
                JLabel[] inputLabels = new JLabel[ 5 ];
                JTextField[] inputTextFields = new JTextField[ 5 ];

                ( ( FlowLayout ) inputLabelsPanel.getLayout ( ) ).setHgap ( 60 );

                for ( int i = 0; i < 5; i++ ) {
                    inputLabels[ i ] = new JLabel ( );
                    inputLabels[ i ].setForeground ( Color.WHITE );
                    inputLabelsPanel.add ( inputLabels[ i ] );

                    inputTextFields[ i ] = new JTextField ( );
                    inputTextFields[ i ].setPreferredSize ( new Dimension ( 100, 20 ) );
                    inputTextFieldsPanel.add ( inputTextFields[ i ] );
                }

                inputLabels[ 0 ].setText ( "ID" );
                inputLabels[ 1 ].setText ( "clientID" );
                inputLabels[ 2 ].setText ( "productID" );
                inputLabels[ 3 ].setText ( "quantity" );
                inputLabels[ 4 ].setText ( "address" );
            }
        }

        inputFrame.pack ( );
        inputFrame.setResizable ( false );
        Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        inputFrame.setLocation ( ( screenSize.width - inputFrame.getWidth ( ) ) / 2, ( screenSize.height - inputFrame.getHeight ( ) ) / 2 );
        inputFrame.setVisible ( true );
    }

    public String[] getUpdateInputs ( ) {
        inputFrame.setVisible ( false );
        int noInputs = inputTextFieldsPanel.getComponentCount ( );

        String[] inputs = new String[ noInputs ];
        for ( int i = 0; i < noInputs; i++ ) {
            inputs[ i ] = ( ( JTextField ) inputTextFieldsPanel.getComponent ( i ) ).getText ( );
        }
        for ( JButton button : tableButtons )
            button.setEnabled ( true );

        return inputs;
    }

}

//    public String[] getInputs ( ) {
//    }
//    public String[] getInputs ( Table table ) {
//        String[] inputs;
//        switch ( table ) {
//            case CLIENT -> {
//                inputs = [];
//            }
//            case PRODUCT -> {
//            }
//            case ORDER -> {
//            }
//            case BILL -> {
//            }
//        }
//    }

