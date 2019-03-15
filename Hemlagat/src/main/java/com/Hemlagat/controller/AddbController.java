package com.Hemlagat.controller;

import com.Hemlagat.model.Addb;
import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.session.ShoppingCart;

import com.Hemlagat.model.AddbFacade;
import com.Hemlagat.model.RatingFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.UserdbFacade;
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
    private RatingFacade ratingFacade;
    @Inject
    private ShoppingCart cart;

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
            addbBean.getCurrent().setStatus("sold");

            final Userdb userdb = userFacade.find(userBean.getEmail());
            addbBean.getCurrent().setUserid(userdb);
            System.out.println("###############################################   jag är på controller" + userdb);
            //  addbBean.getCurrent().setRating(ratingFacade.findMedianRatings(addbBean.getCurrent().getUserid().getEmail())); // den här metoden ställer till man kan int skapa en ads om man har den!!!!
            addbFacade.create(addbBean.getCurrent());

            return "Locationpage?faces-redirect=true";
        } catch (Exception e) {

            return "Locationpage.xhtml";
        }
    }

    public String putItems() {

        items = addbFacade.findByAdress(addbBean.getAddress());
        return "/addb/List.xhtml?faces-redirect=true";

    }

    public String ItemsbyEmail() {

        final Userdb userdb = userFacade.find(userBean.getEmail());
        addbBean.getCurrent().setUserid(userdb);
        itemsbyEmail = addbFacade.findByEmail(userdb);
        return "/addb/extradata.xhtml";

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

    // public double getTotal(){
    //return addbBean.getCurrent().getQuantity() * addbBean.getCurrent().getPrice();
    // }
}
