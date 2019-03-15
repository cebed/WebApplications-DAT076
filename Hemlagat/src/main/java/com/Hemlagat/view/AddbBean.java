package com.Hemlagat.view;

import com.Hemlagat.model.Addb;
import com.Hemlagat.model.AddbFacade;
import com.Hemlagat.model.RatingFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.UserdbFacade;
import com.Hemlagat.model.session.ShoppingCart;
import com.Hemlagat.model.session.UserBean;


import java.io.Serializable;

import java.util.List;

import javax.inject.Named;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;


@Named("addbBean")
@ViewScoped

public class AddbBean implements Serializable {
      @Getter
    @Setter
    private UploadedFile file;
    @Getter
    @Setter
    private Addb current ;
    
    @Getter
    @Setter
    private String email;
   

   @Inject
   private RatingFacade ratingFacade;

    @Inject
    private ShoppingCart cart;
   
    
    
 @PostConstruct
    public void init() {
       
        if (cart.getItem() != null){
            current = cart.getItem();
            email = current.getUserid().getEmail();
            System.out.println("###########################       WE GOT IN THE IF" + current.toString());
        }
        else{
            current = new Addb();
            System.out.println("###########################       NOT NOT NOT NOT" + current.toString());
        
        }
        System.out.println("###########################       statt" + current.toString());
    }

    public int getMedianRating(){
    
      return ratingFacade.findMedianRatings(email);
    }
    
    public List getAllRatings(){
    
     return ratingFacade.findUserRatings(current.getUserid().getEmail());
    }
   
}
