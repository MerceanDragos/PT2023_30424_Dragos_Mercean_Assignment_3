package Models;

import java.lang.reflect.Field;

abstract public class ModelToolkit {

    public static Object[] getColumnNames ( Object[] table ) {
        Object[] columnNames = null;
        if ( table[0] != null ) {
            int i = 0;
            columnNames = new Object[ table[0].getClass ().getDeclaredFields ().length ];
            for ( Field f : table[ 0 ].getClass ( ).getDeclaredFields ( ) ) {
                columnNames[ i ] = f.getName ( );
                i++;
            }
        }
        return columnNames;
    }

    public static Object[][] getData ( Object[] table ) {
        Object[][] data = null;
        if ( table[0] != null ) {
            data = new Object[ table.length ][ table[ 0 ].getClass ( ).getDeclaredFields ( ).length ];
            int i = 0;
            int j = 0;
            for ( Object o : table ) {
                for ( Field f : table[ i ].getClass ( ).getDeclaredFields ( ) ) {
                    f.setAccessible ( true );
                    try {
                        data[ i ][ j ] = f.get ( table[ i ] ).toString ( );
                    } catch ( IllegalAccessException e ) {
                        throw new RuntimeException ( e );
                    }
                    j++;
                }
                i++;
                j = 0;
            }
        }
        return data;
    }
}
