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

    KONZERT( 0 ), COMEDY( 1 ), KULTUR( 2 ), MESSE( 3 ), SPORT( 4 ), THEATHER( 5 ), MUSICAL( 6 );

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
