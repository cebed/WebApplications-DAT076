/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rick
 */
@Stateless
public class RatingFacade extends AbstractFacade<Rating> {

    @PersistenceContext(unitName = "com.mycompany_Hemlagat_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingFacade() {
        super(Rating.class);
    }

    public List<Rating> findUserRatings(String chef) {
        System.out.println("Looking for chefs rating: " + chef);

        List<Rating> getAllRatings = em.createNamedQuery("Rating.findAll", Rating.class).getResultList();
        List<Rating> OnlyWanteAdd = new LinkedList<>();
        Iterator<Rating> ratingIterator = getAllRatings.iterator();
 
        while(ratingIterator.hasNext()) {
             System.out.println(ratingIterator.next().toString()); 
        }
        
        
        for (Rating rate : getAllRatings) {
            System.out.println("Here comes the comments: " + rate.getComment());
            if (rate.getChef().equals(chef)) {
               
                OnlyWanteAdd.add(rate);
            }
        }

      
        return OnlyWanteAdd;
    }
}
