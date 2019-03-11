package com.Hemlagat.service.database;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class DatabaseInitializer {
    @PostConstruct
    private void init() {
        //om databas tom, fyll med data
        System.out.println("deployad!!!!!!");
    }
}
