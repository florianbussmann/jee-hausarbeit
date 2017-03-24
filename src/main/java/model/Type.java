/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 22.03.2017 - 21:58:04
 */
package model;


/**
 * @author agraf
 *
 */
public enum Type {

    Konzert( 0 ), Comedy( 1 ), Kultur( 2 ), Messe( 3 ), Sport( 4 ), Theather( 5 ), Musical( 6 );

    private int id;

    private Type( final int id ) {
        this.id = id;
    }

    public static Type getType( final Integer id ) {

        if ( id == null ) {
            return null;
        }

        for ( Type type : Type.values() ) {
            if ( id.equals( type.getId() ) ) {
                return type;
            }
        }
        throw new IllegalArgumentException( "No matching type for id " + id );
    }

    public int getId() {
        return this.id;
    }
}
