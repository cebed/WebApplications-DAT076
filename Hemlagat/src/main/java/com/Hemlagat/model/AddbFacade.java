/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.model;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    public Addb findByAdress(String address) {
        System.out.println("Looking for address " + address);
        System.out.println("address >" + address + "<");
        QAddb tableofAddress = QAddb.addb;
        System.out.println(query.from(tableofAddress).fetch());
        Addb result = qf.selectFrom(tableofAddress).where(tableofAddress.address.eq(address)).fetchOne();
        System.out.println(qf.selectFrom(tableofAddress).where(tableofAddress.address.eq(address)).fetch());
        if (result != null) {
            System.out.println("Found address");
            System.out.println("address: >" + result.getAddress()+ "<");
        }
        if (result != null && result.getAddress().equals(address)) {
            System.out.println("Found address ");
            return result;
        } else {
            System.out.println("Did not find address");
            return null;
        }
    }
    
    
   
    
}

    
    
    
    

