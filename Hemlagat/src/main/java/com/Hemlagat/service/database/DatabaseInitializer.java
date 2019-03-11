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
        
        
      
        
        
        
        
        
        
    }
}
