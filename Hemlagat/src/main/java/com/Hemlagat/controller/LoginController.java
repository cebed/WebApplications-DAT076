/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.controller;

import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.UserdbFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.session.UserBean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
@Named(value = "login")
@RequestScoped
public class LoginController implements Serializable {

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    @Inject
    private UserdbFacade userRegistry;

    @Inject
    private UserBean userBean;

    @EJB
    private com.Hemlagat.model.AddbFacade ejbFacade;

    public String login() {
        final Userdb loggedInUser = userRegistry.findUser(email, password);
        if (loggedInUser != null) {
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", email);
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", loggedInUser.getUsername());
            userBean.setEmail(email);

            userBean.setUsername(loggedInUser.getUsername());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome:" + loggedInUser.getUsername()));

            return "addb/Create?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong email or password"));

            return null;
        }
    }

    public void showLoginMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Logged in as " + userBean.getUsername()));
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}
