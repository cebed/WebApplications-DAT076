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
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rick
 */
public class RatingdbFacade extends AbstractFacade<Ratingdb> {
    

    @PersistenceContext(unitName = "com.mycompany_Hemlagat_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private JPAQuery query;
    private JPAQueryFactory qf;
    
     @PostConstruct
    public void setup() {
        query = new JPAQuery(em);
        qf = new JPAQueryFactory(em);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingdbFacade() {
        super(Ratingdb.class);
    }

    public Ratingdb findUser(String email, String password) {
        System.out.println("Looking for user " + email);
        System.out.println("Password >" + password + "<");
        QUserdb tableofuser = QUserdb.userdb;
        QRatingdb tableofuser = QRatingdb.rating;
        
        System.out.println(query.from(tableofuser).fetch());
        Userdb result = qf.selectFrom(tableofuser).where(tableofuser.email.eq(email)).fetchOne();
        System.out.println(qf.selectFrom(tableofuser).where(tableofuser.email.eq(email)).fetch());
        if (result != null) {
            System.out.println("Found user");
            System.out.println("Password: >" + result.getPassword() + "<");
        }
        if (result != null && result.getPassword().equals(password)) {
            System.out.println("Found user and right password");
            return result;
        } else {
            System.out.println("Did not find user");
            return null;
        }
    }
    
    
     public Addb findByAddress(String address) {
        System.out.println("Looking for  " + address);
      
        QAddb tableofuser = QAddb.addb;
        System.out.println(query.from(tableofuser).fetch());
       
        Addb result = qf.selectFrom(tableofuser).where(tableofuser.address.eq(address)).fetchOne();
       
        
       
        if (result != null) {
            System.out.println("Found user");
            System.out.println("Password: >" + result.getAddress() + "<");
        }
        
            System.out.println("Did not find user");
            return null;
        }
    
}

    
    
    
    