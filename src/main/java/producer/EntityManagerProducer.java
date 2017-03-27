/*
 * COPYRIGHT:
 *
 * TITLE TO THE CODE REMAIN WITH ZEB/INFORMATION.TECHNOLOGY. THE CODE IS COPYRIGHTED AND PROTECTED BY LAW. YOU WILL NOT
 * REMOVE ANY COPYRIGHT NOTICE FROM THE CODE. REASSEMBLING, RECOMPILATION, TRANSFER, DISTRIBUTION OR MODIFICATION OF
 * PART OR ALL OF THE CODE IN ANY FORM WITHOUT THE PRIOR WRITTEN PERMISSION OF ZEB/INFORMATION.TECHNOLOGY IS PROHIBITED.
 *
 * created: 23.03.2017 - 23:16:39
 */
package producer;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author agraf
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext( name = "h2" )
    private EntityManager entityManager;

    @Produces
    // @RequestScoped
    public EntityManager createEntityManager() {
        return this.entityManager;
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory( "h2" );
        // return emf.createEntityManager();
    }

    /*
     * public void disposeEm( @Disposes final EntityManager em ) { em.close(); }
     */

}
