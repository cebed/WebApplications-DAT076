/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

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
        Query queryRatingsByChef = em.createNamedQuery("Rating.findByChef");
        queryRatingsByChef.setParameter("chef", chef);
        List<Rating> ratings = queryRatingsByChef.getResultList();
        return ratings;
    }
}
