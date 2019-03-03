/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Hemlagat.controller;

import com.Hemlagat.model.MailSender;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Daniel Cebe
 */
@ManagedBean(name = "mailgctr")
@SessionScoped

public class MailController implements Serializable {

    private String fromMail;
    private String username;
    private String password;
    private String toMail;
    private String subject;
    private String message;

    public MailController() {

        this.toMail = "cebedaniel97@gmail.com";
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void send() {

        try {

            MailSender mailSender = new MailSender();
            mailSender.sendMail(fromMail, username, password, toMail, subject, message);
            showSuccessMessage();

        } catch (Exception e) {
            showFailMessage();
        }
    }

    public void showSuccessMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Email has been sent to: " + getToMail()));
    }
    
    public void showFailMessage(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Email could not be sent to: " + getToMail()));
        
    }

}
