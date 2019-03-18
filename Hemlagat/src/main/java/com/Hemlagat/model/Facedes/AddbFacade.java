
package com.Hemlagat.model.Facedes;

import com.Hemlagat.model.Addb;
import com.Hemlagat.model.Userdb;
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
 * @author Nurabd
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
        List<Addb> onlyAktiv = new LinkedList<>();
        
       for(Addb li : getByAddre){
           if(li.getStatus().equals("Aktiv")){
           if(li.getOther().equals(address)){
               
              
                
                OnlyWanteAdd.add(li);       
               } else{
           onlyAktiv.add(li);
           }         
       }
       }
       
          if (!OnlyWanteAdd.isEmpty() ) {
         
            return OnlyWanteAdd;
        } else {
          
            return onlyAktiv;
        }
        }
    
    
    
    public List<Addb> findByEmail(Userdb usdb) {
       
        
       
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
       for(Addb li : emaillist){
           if(usdb.equalsbbb(li.getUserid())){
               
                wantedEmail.add(li);       
               }          
       }
       
          if (!wantedEmail.isEmpty() ) {
         
            return wantedEmail;
        } else {
         
            return null;
        }
        }
    
    
    
    public List<Addb> findonlysoldaAds(Userdb usdb) {
       
        
      
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
       for(Addb li : emaillist){
          
         
           if((usdb.equalsbbb(li.getUserid())) && (li.getOther()!= null) && (li.getStatus().equals("Aktiv"))  ){
               
           
              
              
             
                wantedEmail.add(li);       
               }          
       }
       
          if (!wantedEmail.isEmpty() ) {
          
            return wantedEmail;
        } else {
           
            return null;
        }
        }
    
    
    
    
    
    public List<Addb> findonlyBougtItems(Userdb usdb) {
       
        
      
        List<Addb> emaillist = em.createNamedQuery("Addb.findAll", Addb.class).getResultList();
        List<Addb> wantedEmail = new LinkedList<>();
       for(Addb li : emaillist){
           if((usdb.equalsbbb(li.getUserid())) && (li.getOther()!= null) && (li.getStatus().equals("kopt"))){
             
              
          
                wantedEmail.add(li);       
               }          
       }
       
          if (!wantedEmail.isEmpty() ) {
         
            return wantedEmail;
        } else {
        
            return null;
        }
        }
  
    
    
    public double getTotal(double quan , int price){
    
    return quan * (double)price;
     
     }
    
    
    
    
}

    
    
    
    

