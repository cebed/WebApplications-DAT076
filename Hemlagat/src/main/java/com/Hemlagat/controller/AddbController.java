package com.Hemlagat.controller;

import com.Hemlagat.model.Addb;
import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.session.ShoppingCart;

import com.Hemlagat.model.Facedes.AddbFacade;
import com.Hemlagat.model.Facedes.RatingFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.Facedes.UserdbFacade;
import com.Hemlagat.model.session.UserBean;
import com.Hemlagat.model.session.AddbBean;

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
    private RatingFacade ratingFacade;
    @Inject
    private ShoppingCart cart;
    @Getter
    @Setter
    private String Address;
    @Getter
    @Setter
    private List<Addb> items;
    @Getter
    @Setter
    private List<Addb> itemsbyEmail;
    @Getter
    @Setter
    private List<Addb> bougtItems;
    @Getter
    @Setter
    private List<Addb> soldItems;

    public String create() {
        try {

            addbBean.getCurrent().setPhoto(addbBean.getFile().getContents());
            addbBean.getCurrent().setStatus("Aktiv");

            final Userdb userdb = userFacade.find(userBean.getEmail());
            addbBean.getCurrent().setUserid(userdb);
            System.out.println("###############################################   jag är på controller" + userdb);
           // addbBean.getCurrent().setRating(ratingFacade.findMedianRatings(addbBean.getCurrent().getUserid().getEmail())); // den här metoden ställer till man kan int skapa en ads om man har den!!!!
            addbFacade.create(addbBean.getCurrent());

            return  SoldItems();
        } catch (Exception e) {

            return "Locationpage.xhtml";
        }
    }
    public String setLocation(){
            return "/addb/List.xhtml?faces-redirect=true";
    
    }
    
    public String setProfilePage(){
            return "/profile.xhtml?faces-redirect=true";
    
    }
    
    
    
    public String putItems() {

        items = addbFacade.findByAdress(Address);
        return "/addb/List.xhtml?faces-redirect=true";

    }

    public String ItemsbyEmail() {

        final Userdb userdb = userFacade.find(userBean.getEmail());
        addbBean.getCurrent().setUserid(userdb);
        itemsbyEmail = addbFacade.findByEmail(userdb);
        return "/addb/searchforallitems.xhtml";

    }

    public String SoldItems() {
        final Userdb userdb = userFacade.find(userBean.getEmail());
        addbBean.getCurrent().setUserid(userdb);
        soldItems = addbFacade.findonlysoldaAds(userdb);
        return "/addb/searchSolditems.xhtml";

    }

    public String BougtItems() {
         final Userdb userdb = userFacade.find(userBean.getEmail());
        addbBean.getCurrent().setUserid(userdb);
        bougtItems = addbFacade.findonlyBougtItems(userdb);
        return "/addb/searchBougtItems.xhtml";

    }

    // public double getTotal(){
    //return addbBean.getCurrent().getQuantity() * addbBean.getCurrent().getPrice();
    // }
    public String addToShoppingCart() {
        cart.setItem(addbBean.getCurrent());
        return "/addb/View";
    }
    
    public String addToShoppingCartForRating() {
        cart.setItem(addbBean.getCurrent());
        return "/RatingList";
    }
    
    public String addToShoppingCartForRatingPage() {    
          Addb db = new Addb();
      
        db.setTitle(addbBean.getCurrent().getTitle());
        db.setDescription(addbBean.getCurrent().getDescription());
        db.setStatus("kopt");
        db.setAddress(addbBean.getCurrent().getAddress());
        db.setOther(addbBean.getCurrent().getOther());
        db.setOther2(addbBean.getCurrent().getOther2());
        
        db.setWeight(addbBean.getCurrent().getWeight());
        db.setPrice(addbBean.getCurrent().getPrice());
        
         final Userdb userdb = userFacade.find(userBean.getEmail());
        db.setUserid(userdb);
       addbFacade.create(db);
        
        if((addbBean.getCurrent().getQuantity()-1) > 0){
   
            
            addbBean.getCurrent().setQuantity(addbBean.getCurrent().getQuantity()-1);
        addbFacade.edit(addbBean.getCurrent());
        }
        else{
        addbBean.getCurrent().setStatus("slut");
        addbFacade.edit(addbBean.getCurrent());
        
        }
        
        
        cart.setItem(addbBean.getCurrent());
        
        return "/Ratingpage";
    }

   
}
