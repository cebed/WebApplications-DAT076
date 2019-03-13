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

    @PostConstruct
    public void setup() {

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddbFacade() {
        super(Addb.class);
    }

    public List<Addb> findByAdress(String address) {

        System.out.println("Looking for address " + address);
        List<Addb> getByAddre = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> OnlyWanteAdd = new LinkedList<>();
        for (Addb li : getByAddre) {
            if (li.getAddress().equals(address)) {
                System.out.println("//////////////////////////////////////" + li.getTitle().equals("gbg"));

                System.out.println(li.getAddress().equals("gbg") + "2222 ");
                OnlyWanteAdd.add(li);
            }
        }

        if (!OnlyWanteAdd.isEmpty()) {
            //  System.out.println("Found user and right password");
            return OnlyWanteAdd;
        } else {
            // System.out.println("Did not find user");
            return getByAddre;
        }
    }

    public List<Addb> findByEmail(String email) {
        Userdb us = new Userdb();

        us.setEmail(email.toLowerCase().trim());

        System.out.println("Looking for User " + email);
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
        for (Addb li : emaillist) {
            if (us.equalsbbb(li.getUserid())) {

                wantedEmail.add(li);
            }
        }

        if (!wantedEmail.isEmpty()) {
            // System.out.println("mission done" + wantedEmail);
            return wantedEmail;
        } else {
            System.out.println("mission faild");
            return null;
        }
    }

    public List<Addb> findonlysoldaAds(String email) {
        Userdb us = new Userdb();

        us.setEmail(email.toLowerCase().trim());

        System.out.println("Looking for User " + email);
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
        for (Addb li : emaillist) {

            if ((us.equalsbbb(li.getUserid())) && (li.getOther() != null) && (li.getOther().equals("sold"))) {

                wantedEmail.add(li);
            }
        }

        if (!wantedEmail.isEmpty()) {
            // System.out.println("mission done" );
            return wantedEmail;
        } else {
            //System.out.println("mission faild");
            return null;
        }
    }

    public List<Addb> findonlyBougtItems(String email) {
        Userdb us = new Userdb();

        us.setEmail(email.toLowerCase().trim());

        //System.out.println("Looking for User " + email);
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
        for (Addb li : emaillist) {
            if ((us.equalsbbb(li.getUserid())) && (li.getOther() != null) && (li.getOther().equals("kopt"))) {

                //    System.out.println( li.getOther()+" _________-------" );
                wantedEmail.add(li);
            }
        }

        if (!wantedEmail.isEmpty()) {
            // System.out.println("mission done" );
            return wantedEmail;
        } else {
            // System.out.println("mission faild");
            return null;
        }
    }

    public double getTotal(double quan, int price) {

        return quan * (double) price;

    }

}
