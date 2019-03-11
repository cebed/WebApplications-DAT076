package com.Hemlagat.service.database;

import com.Hemlagat.model.Addb;
import com.Hemlagat.model.AddbFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.UserdbFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class DatabaseInitializer {
    @EJB
  private AddbFacade addbFacade;
   @EJB
    private UserdbFacade userbFacade;
    @PostConstruct
    private void init() {
        //om databas tom, fyll med data
        System.out.println("deployad!!!!!!");
        
        Addb db = new Addb();
        db.setTitle("PASTA CARBONARA");
        db.setDescription("Spaghetti carbonara – en älskad favorit med rökt fläsk eller bacon och grädde! Lika bra till släktmiddagen som till fredagsmyset.");
        db.setStatus("sold");
        db.setAddress("Gothenburg");
        db.setOther("hörsalsvägen 11");
        db.setOther2("23344");
        
        db.setWeight(0.3);
        db.setPrice(30.0);
        db.setQuantity(5);
        
        db.setGluten(true);
        db.setLactose(true);
        db.setNuts(true);
        db.setVegetarian(true);
        
        Userdb us = new Userdb();
        us.setEmail("alla");
        us.setPassword("11");
        us.setUsername("11");
        
        db.setUserid(us);
        userbFacade.create(us);
       
        addbFacade.create(db);
        
      
        
        
        
        
        
        
    }
}
