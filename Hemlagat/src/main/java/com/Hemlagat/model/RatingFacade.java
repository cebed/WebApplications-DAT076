/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rick
 */
public class RatingFacade {

    public RatingFacade() {
    }

    public void insertDB(Rating rating) {
        System.out.println("We are in insertDB");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_Hemlagat_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        try {
            System.out.println("we are in try insterDV1");
            manager.getTransaction().begin();
            System.out.println("we are in try insterDV2");
            manager.persist(rating);
            System.out.println("we are in try insterDV3");
            manager.getTransaction().commit();
            System.out.println("we are in try insterDV4");
        } catch (Exception e) {
            System.out.println("We are i n cathch");
        }

    }

}
