/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.controller;

import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.Addb;
import com.Hemlagat.model.AddbFacade;
import com.Hemlagat.model.UserdbFacade;
import com.Hemlagat.model.Userdb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Daniel Cebe
 */
@Named(value = "loginer")
@SessionScoped
public class LoginController1 implements Serializable {

    @Getter
    @Setter
    private String address;
    @Getter
    @Setter

    @Inject
    private AddbFacade addressreg;
    private Addb loggedAddress;

    public Addb getAddr() {
        return loggedAddress;
    }

    public String login() {
        loggedAddress = addressreg.findByAdress(address);
        if (loggedAddress != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("address", address);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success"));

            return "Logout?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong address"));

            return null;
        }
    }
    
    public void showLoginMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Logged in"));
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}
