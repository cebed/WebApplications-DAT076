/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model.Facedes;

import com.Hemlagat.model.Rating;
import com.Hemlagat.model.Userdb;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
            
        System.out.println("ADDED TO LIST: ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        
        System.out.print("Vi är i min create");
        getEntityManager().persist(rate);
        System.out.print("Vi är i min create2");
    
    }
    
    public int findMedianRatings(String chef){
        int median = 0;
        int count =0;
         List<Rating> allRatings = em.createQuery("SELECT e FROM Rating e").getResultList();

       
        List<Rating> RatingsWeWant = new LinkedList<>();
        for (Rating rate : allRatings) {
            System.out.println("Here comes the comments: " + rate.getRate()); 
            if (rate.getChef().equals(chef)) {
               median += rate.getRate();
               count++;
            }
        }
        if(count != 0){
            median = median/count;}
        System.out.println("THE RATING FOR CHEF " +  chef + " IS :::::" +median + " ::::");
        System.out.println("THE RATING FOR CHEF " +  chef + " IS :::::" +median + " ::::");
        System.out.println("THE RATING FOR CHEF " +  chef + " IS :::::" +median + " ::::");
        System.out.println("THE RATING FOR CHEF " +  chef + " IS :::::" +median + " ::::");
        System.out.println("THE RATING FOR CHEF " +  chef + " IS :::::" +median + " ::::");
        System.out.println("THE RATING FOR CHEF " +  chef + " IS :::::" +median + " ::::");
        
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
