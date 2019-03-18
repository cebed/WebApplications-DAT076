package com.Hemlagat.controller;

import com.Hemlagat.model.Addb;
import com.Hemlagat.model.session.ShoppingCart;

import com.Hemlagat.model.Facedes.AddbFacade;
import com.Hemlagat.model.Facedes.RatingFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.Facedes.UserdbFacade;
import com.Hemlagat.model.session.UserBean;
import com.Hemlagat.model.session.AddbBean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author nurabd
 */
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

    public String create() {
        try {

            addbBean.getCurrent().setPhoto(addbBean.getFile().getContents());
            addbBean.getCurrent().setStatus("Aktiv");
            final Userdb userdb = userFacade.find(userBean.getEmail());
            addbBean.getCurrent().setUserid(userdb);
            addbBean.getCurrent().setRating(ratingFacade.findMedianRatings(addbBean.getCurrent().getUserid().getEmail())); // den här metoden ställer till man kan int skapa en ads om man har den!!!!
            addbFacade.create(addbBean.getCurrent());
            return OnAktiveItems();
        } catch (Exception e) {

            return "Locationpage.xhtml";
        }
    }

    
     public String OnputItems() {
        return "/addb/List.xhtml?faces-redirect=true";
    }
     public String OnItemsbyEmail() {
        return "/addb/searchforallitems.xhtml";
    }
      public String OnAktiveItems() {
        return "/addb/searchSolditems.xhtml";
    }
    
    public String setLocation() {
        return "/addb/List.xhtml?faces-redirect=true";

    }
    public String OnBougtItems() {
        return "/addb/searchBougtItems.xhtml";
    }

    public String addToShoppingCart() {
        cart.setItem(addbBean.getCurrent());
        return "/addb/View";
    }

    public String addToShoppingCartForRating() {
        cart.setItem(addbBean.getCurrent());
        return "/RatingList";
    }

    public String addToShoppingCartForRatingPage() {

        changeStatustofinish();
        if ((addbBean.getCurrent().getQuantity() - 1) > 0) {

            addbBean.getCurrent().setQuantity(addbBean.getCurrent().getQuantity() - 1);
            addbFacade.edit(addbBean.getCurrent());
        } else {
            addbBean.getCurrent().setStatus("slut");
            addbFacade.edit(addbBean.getCurrent());

        }

        cart.setItem(addbBean.getCurrent());

        return "/Ratingpage";
    }

    /* på grund av tid brist så är denna metodenn till att indikera att en ads är köpt, 
       obs, att varje ads som köps klonas för att lösa uppgiften, i och med att en vara erhålls ev endast en användare, 
       så är metoden findbought items dålig konstruerad,
   
     */
    public void changeStatustofinish() {

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

    }

}
