/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    public RatingController() {
    }
    @Setter
    @Getter
    private String comment;
    private Integer rating1;   
    private Integer rating2;   
    private Integer rating3;   
    private Integer rating4 = 3;
     
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
    
    
}
