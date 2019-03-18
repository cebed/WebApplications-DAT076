
package com.Hemlagat.model.Facedes;

import com.Hemlagat.model.Rating;
import com.Hemlagat.model.Userdb;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Rick
 */
@Stateless
public class RatingFacade extends AbstractFacade<Rating> {

    @PersistenceContext(unitName = "com.mycompany_Hemlagat_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Inject
    private UserdbFacade Userfacade;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingFacade() {
        super(Rating.class);
    }
    @Override
    public void create(Rating rate){
         System.out.println(rate.getComment());
         Userdb userdb = Userfacade.find(rate.getChef());
         userdb.getRatings().add(rate);
            
        System.out.println("ADDED TO LIST: ");
        
        System.out.print("Vi är i min create");
        getEntityManager().persist(rate);
    
    
    }
    
    public int findMedianRatings(String chef){
        int median = 0;
        int count =0;
         List<Rating> allRatings = em.createQuery("SELECT e FROM Rating e").getResultList();

       
        
        for (Rating rate : allRatings) {
            System.out.println("Here comes the comments: " + rate.getRate()); 
            if (rate.getChef().equals(chef)) {
               median += rate.getRate();
               count++;
            }
        }
        if(count != 0){
            median = median/count;}
        
        
        return median;
    
    
    }

    public List<Rating> findUserRatings(String chef) {
        
       
        List<Rating> allRatings = em.createQuery("SELECT e FROM Rating e").getResultList();

       
       List<Rating> RatingsWeWant = new LinkedList<>();
        for (Rating rate : allRatings) {
            System.out.println("Here comes the comments: " + rate.getComment()); 
            if (rate.getChef().equals(chef)) {
               
               RatingsWeWant.add(rate);
            }
        }

       return RatingsWeWant;
    }
}
