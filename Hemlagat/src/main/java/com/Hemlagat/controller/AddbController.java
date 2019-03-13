package com.Hemlagat.controller;

import com.Hemlagat.model.Addb;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.controller.util.JsfUtil;

import com.Hemlagat.model.AddbFacade;
import com.Hemlagat.model.UserdbFacade;
import com.Hemlagat.model.session.ShoppingCart;
import com.Hemlagat.model.session.UserBean;
import com.Hemlagat.view.AddbBean;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;


import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

@Named("addbController")
@SessionScoped
public class AddbController implements Serializable {

    @Inject
    private AddbBean addbBean;
    @Inject
    private UserBean userBean;
    
    
   @Inject
  private AddbFacade addbFacade;
    @Inject
  private UserdbFacade userFacade;
    
    @Inject
    private ShoppingCart cart;
    

    
    public String create() {
        try {
              System.out.println("###############################################   jag är på controller" + addbBean.getCurrent().toString());       
            addbBean.getCurrent().setPhoto(addbBean.getFile().getContents()); 
            addbBean.getCurrent().setStatus("sold");
            
            final Userdb userdb = userFacade.find(userBean.getEmail());
          addbBean.getCurrent().setUserid(userdb);
            
             
            addbFacade.create( addbBean.getCurrent());
            return "Locationpage?faces-redirect=true";
        } catch (Exception e) {
          
            return "Locationpage.xhtml";
        }
    }

    public String putItems() {

       
     //addbBean.setItems();
       return  "/addb/List.xhtml?faces-redirect=true";
       
    }
    
     
     
    public String addToShoppingCart() {
        cart.setItem(addbBean.getCurrent());
        return "/addb/View";
    }
    // public double getTotal(){
         
        
    //return addbBean.getCurrent().getQuantity() * addbBean.getCurrent().getPrice();
    
     
    // }

}
