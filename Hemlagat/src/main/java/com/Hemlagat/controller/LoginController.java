
package com.Hemlagat.controller;


import com.Hemlagat.controller.util.JsfUtil;
import com.Hemlagat.model.Facedes.UserdbFacade;
import com.Hemlagat.model.Userdb;
import com.Hemlagat.model.session.UserBean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    private String password;

    
    @Inject
    private UserdbFacade userRegistry;

    @Inject
    private UserBean userBean;

    @EJB
    private com.Hemlagat.model.Facedes.AddbFacade ejbFacade;

    public String login() {
        final Userdb loggedInUser = userRegistry.findUser(email, password);
        if (loggedInUser != null) {
            
            userBean.setEmail(email);

            userBean.setUsername(loggedInUser.getUsername());

            return "Locationpage?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong email or password"));

            return null;
        }
    }
    /**
     * Can be used for future development
     * 
    public void showLoginMessage(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Logged in as " + userBean.getUsername()));
    }
    */
    public void showLogoutMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Logged out"));
        JsfUtil.addSuccessMessage("Logged Out");

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        showLogoutMessage();
        return "Locationpage?faces-redirect=true";
    }


}
