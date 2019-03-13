package com.Hemlagat.controller;

import com.Hemlagat.model.Addb;
import com.Hemlagat.controller.util.JsfUtil;

import com.Hemlagat.model.AddbFacade;
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
    
     @Getter
    @Setter
    private List<Addb> items;
     @Getter
    @Setter
    private List<Addb> itemsbyEmail;
    @Getter
    @Setter
    private List<Addb>  bougtItems;    
    @Getter
    @Setter
    private List<Addb>  soldItems; 
                       
    

    
    public String create() {
        try {
              System.out.println("###############################################   jag är på controller" + addbBean.getCurrent().toString());       
            addbBean.getCurrent().setPhoto(addbBean.getFile().getContents()); 
            addbBean.getCurrent().setStatus("sold");
          
             //addbBean.getCurrent().setUserid(userBean.setUserBean(userBean.getEmail()));
             
            addbFacade.create( addbBean.getCurrent());
            return "Locationpage?faces-redirect=true";
        } catch (Exception e) {
          
            return "Locationpage.xhtml";
        }
    }

    public String putItems() {

       
     items = addbFacade.findByAdress(addbBean.getAddress());
       return  "/addb/List.xhtml?faces-redirect=true";
       
    }
    
     public String ItemsbyEmail() {

       
            itemsbyEmail =  addbFacade.findByEmail(addbBean.getEmail());
            return "/addb/extradata.xhtml";
        
    }
     
     
     public String SoldItems() {

          soldItems = addbFacade.findonlysoldaAds(addbBean.getEmail());
           return "/addb/extradata.xhtml";
     
    }
   
     
     public String BougtItems() {

        bougtItems = addbFacade.findonlysoldaAds(addbBean.getEmail());
       return "/addb/extradata.xhtml";
        
    }
     
     
    // public double getTotal(){
         
        
    //return addbBean.getCurrent().getQuantity() * addbBean.getCurrent().getPrice();
    
     
    // }

}
