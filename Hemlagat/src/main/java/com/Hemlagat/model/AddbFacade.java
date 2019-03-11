/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel Cebe
 */
@Stateless
public class AddbFacade extends AbstractFacade<Addb> {

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

    public AddbFacade() {
        super(Addb.class);
    }

    public List<Addb> findByAdress(String address) {
        Userdb us = new Userdb();
        Userdb us1 = new Userdb();
        us.setEmail("nur");
        us.setPassword("11");
        us.setUsername("11");
         us1.setEmail("nurhusein11@gmail.com");
        System.out.println("Looking for address " + address);
        List<Addb> getByAddre = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> OnlyWanteAdd = new LinkedList<>();
       for(Addb li : getByAddre){
           if(li.getAddress().equals(address)){
               Userdb b = (Userdb)li.getUserid();
              
                System.out.println( us1.equalsbbb(b) + "2222 " +us1 + " _________-------" + b);
                OnlyWanteAdd.add(li);       
               }          
       }
       
          if (!OnlyWanteAdd.isEmpty() ) {
            System.out.println("Found user and right password");
            return OnlyWanteAdd;
        } else {
            System.out.println("Did not find user");
            return getByAddre;
        }
        }
    
    
    
    public List<Addb> findByEmail(String email) {
        Userdb us = new Userdb();
        
        us.setEmail(email.toLowerCase().trim());
        
        System.out.println("Looking for User " + email);
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
       for(Addb li : emaillist){
           if(us.equalsbbb(li.getUserid())){
              
              
                System.out.println( us.equalsbbb(li.getUserid()) + "2222 " +us + " _________-------" + li.getUserid());
                wantedEmail.add(li);       
               }          
       }
       
          if (!wantedEmail.isEmpty() ) {
            System.out.println("mission done");
            return wantedEmail;
        } else {
            System.out.println("mission faild");
            return null;
        }
        }
    
    
  
    
    
}

    
    
    
    

