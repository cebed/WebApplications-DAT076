/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.controller;

import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.UserdbFacade;
import com.Hemlagat.model.Userdb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
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
    private Userdb loggedInUser;

    public Userdb getUser() {
        return loggedInUser;
    }

    public String login() {
        loggedInUser = userRegistry.findUser(email, password);
        if (loggedInUser != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", email);
            return "Logout?faces-redirect=true";

        } else {
            JsfUtil.addErrorMessage("Wrong email or password");

            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

}
