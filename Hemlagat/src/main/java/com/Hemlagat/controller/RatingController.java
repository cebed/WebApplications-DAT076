package com.Hemlagat.controller;

import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.Rating;
import com.Hemlagat.model.RatingFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RateEvent;

/**
 *
 * @author Rick
 */
@Named(value = "ratingbean")
@SessionScoped
public class RatingController implements Serializable {

    /**
     * Creates a new instance of Ratingbean
     */
    @Setter
    @Getter
    private String comment;
    private Integer rating1;
    private Integer rating2;
    private Integer rating3;
    private Integer rating4 = 3;
    private Rating rating;
    private RatingFacade facade;
   /* @EJB(mappedName = "RatingFacade")
    private com.Hemlagat.model.RatingFacade ejbFacade;*/

    public RatingController() {
    }

    /*private RatingFacade getFacade() {
        return ejbFacade;
    }*/

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getRating1() {
        return rating1;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }

    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }

    public Integer getRating4() {
        return rating4;
    }

    public void setRating4(Integer rating4) {
        this.rating4 = rating4;
    }

    public void setRatingOnUser() {
        System.out.print("we are hereOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

        rating = new Rating();

        rating.setRate(23);
        rating.setComment("comment");
        rating.setRater("the persong giving the rate");
        rating.setChef("the chef");
        facade = new RatingFacade();
        facade.insertDB(rating);
       /* try {
            getFacade().create(rating);

        } catch (Exception e) {
            JsfUtil.addErrorMessage("rating error for putting in database");
        }*/
    }
    
}
