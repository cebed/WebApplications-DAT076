package com.Hemlagat.model.session;

import com.Hemlagat.controller.AddbController;
import com.Hemlagat.model.Addb;
import com.Hemlagat.model.Facedes.AddbFacade;
import com.Hemlagat.model.Facedes.RatingFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.Facedes.UserdbFacade;
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
    private Addb current;

    @Getter
    @Setter
    private String email;

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
    private List<Addb> aktiveItems;

    @Inject
    private RatingFacade ratingFacade;

    @Inject
    private ShoppingCart cart;

    @Inject
    private AddbController addbController;

    @Inject
    private AddbFacade addbFacade;
    @Inject
    private UserdbFacade userFacade;
    @Inject
    private UserBean userBean;

    @PostConstruct
    public void init() {

        if (cart.getItem() != null) {
            current = cart.getItem();
            email = current.getUserid().getEmail();
        } else {
            current = new Addb();
        }
    }

    public int getMedianRating() {

        return ratingFacade.findMedianRatings(email);
    }

    public List getAllRatings() {

        return ratingFacade.findUserRatings(current.getUserid().getEmail());
    }

    public List<Addb> getItems() {
        return addbFacade.findByAdress(addbController.getAddress());
    }

    public String putItems() {
        return "/addb/List.xhtml?faces-redirect=true";
    }

    public List<Addb> getitemsbyEmail() {
        final Userdb userdb = userFacade.find(userBean.getEmail());
        current.setUserid(userdb);
        return addbFacade.findByEmail(userdb);
    }

    public String OnItemsbyEmail() {
        return "/addb/searchforallitems.xhtml";
    }

    public List<Addb> getaktiveItems() {
        final Userdb userdb = userFacade.find(userBean.getEmail());
        current.setUserid(userdb);
        return addbFacade.findonlysoldaAds(userdb);

    }

    public String OnAktiveItems() {
        return "/addb/searchSolditems.xhtml";
    }

    public List<Addb> getBougtItems() {
        final Userdb userdb = userFacade.find(userBean.getEmail());
        current.setUserid(userdb);
        return addbFacade.findonlyBougtItems(userdb);

    }

    public String OnBougtItems() {
        return "/addb/searchBougtItems.xhtml";
    }

}
